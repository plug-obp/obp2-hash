#include <stdint.h>
#include <stdlib.h>
#include <jni.h>
#include "spooky_v2/SpookyV2.h"
#include "fast-hash/fasthash.h"
#include "lookup3/lookup3.h"

JNIEXPORT jboolean JNICALL Java_obp2_hash_SpookyHashV2_hash128 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jlongArray iseed_ohash) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 1;
    }
    jboolean seedsWereCopied = 0;
    void *theSeeds  = (*env)->GetPrimitiveArrayCritical(env, iseed_ohash, &seedsWereCopied);
    if (theBuffer == NULL) {
        return 1;
    }

    uint64_t *seed1 = theSeeds;
    uint64_t *seed2 = ((uint64_t *)theSeeds) + 1;

    // printf("seeds in c : (%llu, %llu)\n", *seed1, *seed2);
    // fflush(stdout);

    spookyhashv2_hash128(theBuffer, buffer_length, seed1, seed2);

    // printf("hashes in c : (%lld, %lld)\n", *seed1, *seed2);
    // fflush(stdout);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);
    (*env)->ReleasePrimitiveArrayCritical(env, iseed_ohash, theSeeds, 0);

    return 0;
}

JNIEXPORT jboolean JNICALL Java_obp2_hash_SpookyHashV2_hash128int4 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jintArray iseed_ohash) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 1;
    }
    jboolean seedsWereCopied = 0;
    void *theSeeds  = (*env)->GetPrimitiveArrayCritical(env, iseed_ohash, &seedsWereCopied);
    if (theBuffer == NULL) {
        return 1;
    }

    uint64_t *seed1 = theSeeds;
    uint64_t *seed2 = ((uint64_t *)theSeeds) + 1;

    // printf("seeds in c : (%llu, %llu)\n", *seed1, *seed2);
    // fflush(stdout);

    spookyhashv2_hash128(theBuffer, buffer_length, seed1, seed2);

    // printf("hashes in c : (%lld, %lld)\n", *seed1, *seed2);
    // fflush(stdout);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);
    (*env)->ReleasePrimitiveArrayCritical(env, iseed_ohash, theSeeds, 0);

    return 0;
}

JNIEXPORT jlong JNICALL Java_obp2_hash_SpookyHashV2_hash64 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jlong seed) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 0;
    }

    uint64_t result = spookyhashv2_hash64(theBuffer, buffer_length, (uint64_t)seed);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);

    return result;
}

JNIEXPORT jint JNICALL Java_obp2_hash_SpookyHashV2_hash32 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jint seed) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 0;
    }

    uint32_t result = spookyhashv2_hash64(theBuffer, buffer_length, (uint32_t)seed);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);

    return result;
}

JNIEXPORT jlong JNICALL Java_obp2_hash_FastHash_hash64 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jlong seed) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 0;
    }

    uint64_t result = fasthash64(theBuffer, buffer_length, (uint64_t)seed);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);

    return result;
}

JNIEXPORT jint JNICALL Java_obp2_hash_FastHash_hash32 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jint seed) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 0;
    }

    uint32_t result = fasthash32(theBuffer, buffer_length, (uint32_t)seed);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);

    return result;
}

JNIEXPORT jint JNICALL Java_obp2_hash_JenkinsLookup3_hash32h1 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jint seed) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 0;
    }

    uint32_t result = lookup3_hashlittle(theBuffer, buffer_length, (uint32_t)seed);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);

    return result;
}

JNIEXPORT jboolean JNICALL Java_obp2_hash_JenkinsLookup3_hash32h2 (
    JNIEnv *env, jobject self, jbyteArray ibuffer, jintArray iseed_ohash) {

    size_t buffer_length = (size_t)((*env)->GetArrayLength(env, ibuffer));
    if (buffer_length < 1) {
        //nothing to do
        return 0;
    }

    jboolean bufferWasCopied = 0;
    void *theBuffer = (*env)->GetPrimitiveArrayCritical(env, ibuffer, &bufferWasCopied);
    if (theBuffer == NULL) {
        return 1;
    }
    jboolean seedsWereCopied = 0;
    void *theSeeds  = (*env)->GetPrimitiveArrayCritical(env, iseed_ohash, &seedsWereCopied);
    if (theBuffer == NULL) {
        return 1;
    }

    uint32_t *seed1 = theSeeds;
    uint32_t *seed2 = ((uint32_t *)theSeeds) + 1;

    lookup3_hashlittle2(theBuffer, buffer_length, seed1, seed2);

    (*env)->ReleasePrimitiveArrayCritical(env, ibuffer, theBuffer, 0);
    (*env)->ReleasePrimitiveArrayCritical(env, iseed_ohash, theSeeds, 0);

    return 0;
}