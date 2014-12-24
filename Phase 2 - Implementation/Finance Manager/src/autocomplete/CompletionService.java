package autocomplete;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public interface CompletionService<T> {
    T autoComplete(String startsWith);
}