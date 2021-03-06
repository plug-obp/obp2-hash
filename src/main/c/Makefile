BUILD_DIR=../../../build/objects


CC_FLAGS=-fPIC

ifeq ($(OS),Windows_NT)
    UNAME :=windows
else
    UNAME := $(shell uname -s)
endif

ifeq ($(UNAME),windows)
pla=win32
else
ifeq ($(UNAME),Darwin)
pla=darwin
else
pla=linux
endif
endif

LIB_DIR=../resources/obp2/hash/lib/${UNAME}_x86_64

all: DetectOS

DetectOS:
	-@make $(UNAME)

Darwin: obp2_hash_jni.o fasthash.o spooky_v2.o lookup3.o | ${LIB_DIR}
	gcc $(CPPFLAGS) -fPIC -dynamiclib -Wall \
		${BUILD_DIR}/obp2_hash_jni.o \
		${BUILD_DIR}/fasthash.o \
		${BUILD_DIR}/spooky_v2.o \
		${BUILD_DIR}/lookup3.o \
		-o ${LIB_DIR}/libobp2hash.dylib

Linux: obp2_hash_jni.o fasthash.o spooky_v2.o lookup3.o | ${LIB_DIR}
	gcc $(CPPFLAGS) -fPIC -shared -Wall \
		${BUILD_DIR}/obp2_hash_jni.o \
		${BUILD_DIR}/fasthash.o \
		${BUILD_DIR}/spooky_v2.o \
		${BUILD_DIR}/lookup3.o \
		-o ${LIB_DIR}/libobp2hash.so

windows: obp2_hash_jni.o fasthash.o spooky_v2.o lookup3.o | ${LIB_DIR}
		gcc $(CPPFLAGS) -fPIC -s -shared -Wall \
				-Wl,--subsystem,windows \
         		${BUILD_DIR}/obp2_hash_jni.o \
         		${BUILD_DIR}/fasthash.o \
         		${BUILD_DIR}/spooky_v2.o \
         		${BUILD_DIR}/lookup3.o \
         		-o ${LIB_DIR}/libobp2hash.dll

${BUILD_DIR}:
	mkdir -p ${BUILD_DIR}
${LIB_DIR}:
	mkdir -p ${LIB_DIR}

obp2_hash_jni.o: obp2_hash_jni.c | ${BUILD_DIR}
	gcc ${CC_FLAGS} -I"${JAVA_HOME}"/include -I"${JAVA_HOME}"/include/${pla} -Wall -Werror -c obp2_hash_jni.c -o ${BUILD_DIR}/obp2_hash_jni.o

fasthash.o: fast-hash/fasthash.c | ${BUILD_DIR}
	gcc ${CC_FLAGS} -Wall -Werror -c fast-hash/fasthash.c -o ${BUILD_DIR}/fasthash.o

spooky_v2.o: spooky_v2/SpookyV2.cpp | ${BUILD_DIR}
	gcc $(CC_FLAGS) -Wall -Werror -c spooky_v2/SpookyV2.cpp -o ${BUILD_DIR}/spooky_v2.o 

lookup3.o: lookup3/lookup3.c | ${BUILD_DIR}
	gcc ${CC_FLAGS} -Wall  -Wno-format -Wno-unused-variable -c lookup3/lookup3.c -o ${BUILD_DIR}/lookup3.o

clean:
	rm -rf ${BUILD_DIR}/*

