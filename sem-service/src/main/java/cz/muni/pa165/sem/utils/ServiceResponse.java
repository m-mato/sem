package cz.muni.pa165.sem.utils;

/**
 * @author Matej Majdis
 */
public class ServiceResponse<T> {
	private Result result;
	private T response;

	public enum Result {
		OK, NOT_FOUND, CONFLICT, ERROR
	}

	public static final ServiceResponse OK = new ServiceResponse(Result.OK);
	public static final ServiceResponse NOT_FOUND = new ServiceResponse(Result.NOT_FOUND);
	public static final ServiceResponse CONFLICT = new ServiceResponse(Result.CONFLICT);
	public static final ServiceResponse ERROR = new ServiceResponse(Result.ERROR);

	public ServiceResponse(Result result) {
		this(result, null);
	}

	/**
	 * Constructor with OK result.
	 *
	 * @param response
	 */
	public ServiceResponse(T response) {
		this.result = Result.OK;
		this.response = response;
	}

	public ServiceResponse(Result result, T response) {
		this.result = result;
		this.response = response;
	}

	public Result getResult() {
		return result;
	}

	public T getResponse() {
		return response;
	}
}
