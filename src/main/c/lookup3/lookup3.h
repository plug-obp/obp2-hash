
#ifndef _LOOKUP3_H
#define _LOOKUP3_H

#include <stdint.h>
#include <stddef.h>

void lookup3_hashlittle2( 
  const void *key,       /* the key to hash */
  size_t      length,    /* length of the key */
  uint32_t   *pc,        /* IN: primary initval, OUT: primary hash */
  uint32_t   *pb);       /* IN: secondary initval, OUT: secondary hash */

uint32_t lookup3_hashlittle( const void *key, size_t length, uint32_t initval);

#endif
