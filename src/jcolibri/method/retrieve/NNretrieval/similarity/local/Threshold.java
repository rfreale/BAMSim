package jcolibri.method.retrieve.NNretrieval.similarity.local;

import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;


/**
 * This function returns 1 if the difference between two numbers is less than a
 * threshold, 0 in the other case.
 */
public class Threshold implements LocalSimilarityFunction {


	/** Threshold. */
	double _threshold;

	/**
	 * Constructor.
	 */
	public Threshold(double threshold) {
		_threshold = threshold;
	}

	/**
	 * Applies the similarity function.
	 */
	public double compare(double x, double y) {
		if (Math.abs(x - y) > _threshold)
			return 0;
		else
			return 1;
	}



	/**
	 * Applies the similarity function.
	 * 
	 * @param o1
	 *            Integer
	 * @param o2
	 *            Integer
	 * @return result of apply the similarity function.
	 */
	public double compute(Object o1, Object o2) throws jcolibri.exception.NoApplicableSimilarityFunctionException {
		if ((o1 == null) || (o2 == null))
			return 0;
		if (!(o1 instanceof java.lang.Number))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o1.getClass());
		if (!(o2 instanceof java.lang.Number))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), o2.getClass());


		Number i1 = (Number) o1;
		Number i2 = (Number) o2;
		return (double) compare(i1.doubleValue(), i2.doubleValue());
	}
	
	/** Applicable to Integer */
	public boolean isApplicable(Object o1, Object o2)
	{
		if((o1==null)&&(o2==null))
			return true;
		else if(o1==null)
			return o2 instanceof Integer;
		else if(o2==null)
			return o1 instanceof Integer;
		else
			return (o1 instanceof Integer)&&(o2 instanceof Integer);
	}
}