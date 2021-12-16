package obp2.hash;

import org.junit.Test;

import java.util.Random;

public class ExternalHashTests {

    @Test
    public void testSpooky128() {
        int nb_bytes = 1024;
        int runs = 1000;
        byte[] bytes = new byte[nb_bytes];
        Random rnd = new Random();
        long[] seeds = new long[2];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < runs; i += 2) {
            rnd.nextBytes(bytes);
            seeds[0] = rnd.nextLong();
            seeds[1] = rnd.nextLong();

            SpookyHashV2.hash128(bytes, seeds);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("spooky2_hash128 " + "("+nb_bytes+", "+ runs+") took " + (endTime - startTime) + " milliseconds");
    }
}
