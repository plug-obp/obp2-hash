package obp2.hash;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class JenkinsLookup3 extends ExternalHash {

    // uint64_t fasthash64(const void *buf, size_t len, uint64_t seed);
    private static native long hash32h1(byte[] buffer, int seed);
    public static long hash32(byte[] buffer, int seed) {
        return hash32h1(buffer, seed);
    }
    // uint32_t fasthash32(const void *buf, size_t len, uint32_t seed);
    private static native boolean hash32h2(byte[] buffer, int[] inseed_outhash);
    public static boolean hash32(byte[] buffer, int[]inseed_outhash) {
        return hash32h2(buffer, inseed_outhash);
    }

    private static long testLookup3hash32(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long hash = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            hash = hash32(bytes, rnd.nextInt());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("JenkinsLookup3_hash32 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
        return hash;
    }

    private static void testLookup3hash32_2(int nb_bytes, int runs) {
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        int[] seeds = new int[2];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            seeds[0] = rnd.nextInt();
            seeds[1] = rnd.nextInt();

            hash32(bytes, seeds);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("JenkinsLookup3_hash32_2 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        int size = 50000;
        int runs = 10000;
        testLookup3hash32(size, runs);
        testLookup3hash32_2(size, runs);
        testArraysHashCode(size, runs);
    }
}
