
extern void spookyhashv2_hash128(const void *message,size_t length, uint64_t *hash1, uint64_t *hash2);
uint64_t spookyhashv2_hash64(const void *message,size_t length, uint64_t seed);
uint32_t spookyhashv2_hash32(const void *message,size_t length, uint32_t seed);