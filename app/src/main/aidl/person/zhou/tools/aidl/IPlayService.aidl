// IPlayService.aidl
package person.zhou.tools.aidl;

// Declare any non-default types here with import statements

interface IPlayService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);

    String play();
}
