#install.packages("igraph")

library("igraph")

matrix_csv = read.csv("D:/GitHub/BAMSim/topologias/NSF-14n-42e.csv",head=TRUE, sep=";")

createGraph <- function(dataframe){
      tempnet = graph.data.frame(dataframe, directed = TRUE)

      return (tempnet)
}

createGraph2 <- function(dataframe){
      tempnet = graph.data.frame(dataframe, directed = TRUE)
      temp_adj = get.adjacency(tempnet, attr="custo")
      temp_adj <- as.matrix(temp_adj)
      g_created <- graph.adjacency(temp_adj)
      g_created <- as.undirected(g_created)
      return (g_created)
}
sortestPathByName <- function(net,srcName)
{
	return (shortest_paths(net, from = V(net)[srcName], to  = V(net)[V(net) !=V(net)[srcName]] , output = "both"))
}
edgeName <- function(n)
{
	return (paste(matrix_csv[n,1],matrix_csv[n,2], sep="->"))
}


plotPath <- function(net, path){
	# Generate edge color variable to plot the path:

	ecol <- rep("gray80", ecount(net))

	ecol[unlist(path$epath)] <- "orange"

	# Generate edge width variable to plot the path:
	
	ew <- rep(2, ecount(net))

	ew[unlist(path$epath)] <- 4

	# Generate node color variable to plot the path:

	vcol <- rep("gray40", vcount(net))

	vcol[unlist(path$vpath)] <- "gold"
	plot(net, vertex.color=vcol, edge.color=ecol, edge.width=ew, edge.arrow.mode=0)
}


net <- createGraph(matrix_csv)


#par(mfrow=c(3,5))
#plot(net)

# Calcula a arvore minima
#net.minimum = minimum.spanning.tree(net, weights = E(net)$Custo)
#plot(net.minimum)

# Calcula menor caminho

#for (x in 1:14) 
#{
#	net.path =shortest_paths(net, from = V(net)[x], to  = V(net)[V(net) !=V(net)[x]] , output = "both")
#	print(paste("=== Origem: R", x-1, " ===", sep=""))
#       for (y in 1:length(net.path$epath))
#	{
#		print(paste("===Destino: R", y-1, " ===", sep=""))
#		print(net.path$epath[y])
#	}
#	plotPath(net, net.path)
#
#}






