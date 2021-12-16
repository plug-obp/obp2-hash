package obp2.hash;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SpookyHashV2 extends ExternalHash {
    //void spookyhashv2_hash128(const void *message,size_t length, uint64 *hash1, uint64 *hash2)
    public static native boolean hash128(byte[] buffer, long[] seedin_hashout);

    //uint64_t spookyhashv2_hash64(const void *message,size_t length, uint64_t seed);
    public static native long hash64(byte[] buffer, long seed);

    //uint32_t spookyhashv2_hash32(const void *message,size_t length, uint32_t seed);
    public static native int hash32(byte[] buffer, int seed);


    private static void testSpooky128(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long[] seeds = new long[2];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            seeds[0] = rnd.nextLong();
            seeds[1] = rnd.nextLong();

            hash128(bytes, seeds);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("spooky2_hash128 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
    }

    private static long testSpooky64(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long hash = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            hash = hash64(bytes, rnd.nextLong());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("spooky2_hash64 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
        return hash;
    }

    private static long testSpooky32(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long hash = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            hash = hash32(bytes, rnd.nextInt());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("spooky2_hash32 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
        return hash;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        int size = 50000;
        int runs = 10000;
        testSpooky128(size, runs);
        testSpooky64(size, runs);
        testSpooky32(size, runs);
        testArraysHashCode(size, runs);
    }
}
