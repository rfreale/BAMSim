package jcolibri.method.retrieve.NNretrieval.similarity.local;

import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;


/**
 * This function returns 1 if both individuals are equal, otherwise returns 0
 * This function returns the similarity of two number inside an interval.
 * sim(x,y)=1-(|x-y|/interval)
 * 
 * Now it works with Number values.
 */
public class IntervalEqual implements LocalSimilarityFunction {

	/** Interval */
	double _interval;

	/**
	 * Constructor.
	 */
	public IntervalEqual(double interval) {
		_interval = interval;
	}
	
	/**
	 * Applies the similarity function.
	 * 
	 * @param o1
	 *            Object.
	 * @param o2
	 *            Object.
	 * @return the result of apply the similarity function.
	 */
    public double compute(Object o1, Object o2) throws jcolibri.exception.NoApplicableSimilarityFunctionException{
    	if ((o1 == null) || (o2 == null))
			return 0;
		if (!(o1 instanceof java.lang.Number))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
		if (!(o2 instanceof java.lang.Number))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o2.getClass());

	
		Number i1 = (Number) o1;
		Number i2 = (Number) o2;
		
		double v1 = i1.doubleValue();
		double v2 = i2.doubleValue();
		
		if (v1==0 && v2==0){
			return 1;
		}else if (v1>0 && v2>0){
			return 1 - ((double) Math.abs(v1 - v2) / _interval);	
		}else{
			return 0;
		}
		
    }
    
    /** Applicable to any class */
	public boolean isApplicable(Object o1, Object o2)
	{
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return o2 instanceof Number;
		else if(o2==null)
			return o1 instanceof Number;
		else
			return (o1 instanceof Number)&&(o2 instanceof Number);
	}
}
