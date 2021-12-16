package obp2.hash;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class FastHash extends ExternalHash {

    // uint64_t fasthash64(const void *buf, size_t len, uint64_t seed);
    public static native long hash64(byte[] buffer, long seed);
    // uint32_t fasthash32(const void *buf, size_t len, uint32_t seed);
    public static native int hash32(byte[] buffer, int seed);

    private static long testFastHash64(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long hash = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            hash = hash64(bytes, rnd.nextLong());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("FastHash_hash64 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
        return hash;
    }

    private static long testFastHash32(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long hash = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            hash = hash32(bytes, rnd.nextInt());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("FastHash_hash32 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
        return hash;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        int size = 50000;
        int runs = 10000;
        testFastHash64(size, runs);
        testFastHash32(size, runs);
        testArraysHashCode(size, runs);
    }
}
