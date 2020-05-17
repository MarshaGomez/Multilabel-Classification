#include <openssl/conf.h>
#include <openssl/evp.h>
#include <openssl/err.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include<openssl/rand.h>



int main (void){
   //128 bit key (16 characters * 8 bit)
  unsigned char *key = (unsigned char *)"0123456789012345";
  FILE *fp;
  //Our Plaintext
  fp = fopen("file.txt", "rb");
  fseek(fp, 0, SEEK_END);
  long fsize = ftell(fp);
  fseek(fp, 0, SEEK_SET);
  unsigned char *plaintext = malloc(fsize + 1);
  fread(plaintext, 1, fsize, fp);
  fclose(fp);
  plaintext[fsize] = 0;
  RAND_poll();
  unsigned char iv[16];
  RAND_bytes(iv, 16);
  
  /* Buffer for ciphertext. Ensure the buffer is long enough for the
   * ciphertext which may be longer than the plaintext, depending on the
   * algorithm and mode*/
  unsigned char* ciphertext = (unsigned char *) malloc(sizeof(plaintext)+16);
  int decryptedtext_len, ciphertext_len;
  // Encrypt utility function
  ciphertext_len = encrypt (plaintext, strlen ((char *)plaintext), key, iv, ciphertext);
  free(plaintext);
  // Redirect our ciphertext to the terminal
  //printf("Ciphertext is:\n");
  //BIO_dump_fp (stdout, (const char *)ciphertext, ciphertext_len);
  fp = fopen("file.txt.enc", "wb");
  fwrite(ciphertext, 1, ciphertext_len, fp);
  fclose(fp);
  // Buffer for the decrypted text 
  //unsigned char* decryptedtext = (unsigned char *) malloc(sizeof(ciphertext));
  // Decrypt the ciphertext
  //decryptedtext_len = decrypt(/*Think about what you need!*/);
  // Add a NULL terminator. We are expecting printable text
  //decryptedtext[decryptedtext_len] = '\0';
  // Show the decrypted text 
  //printf("Decrypted text is:\n");
  //printf("%s\n", decryptedtext);
  
  return 0;
}void handleErrors(void)
{
  ERR_print_errors_fp(stderr);
  abort();
}
int encrypt(unsigned char *plaintext, int plaintext_len, unsigned char *key,
  unsigned char *iv, unsigned char *ciphertext)
{
  EVP_CIPHER_CTX *ctx;
  int len;
  int ciphertext_len;
  /* Create and initialise the context */
  ctx = EVP_CIPHER_CTX_new();
  // Encrypt init
  EVP_EncryptInit(ctx, EVP_aes_128_cbc(), key, iv);
  // Encrypt Update: one call is enough because our mesage is very short.
  EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, plaintext_len);
  ciphertext_len = len;
  //Encrypt Final. Finalize the encryption and adds the padding
  EVP_EncryptFinal(ctx, ciphertext + len, &len);
  ciphertext_len += len;
  // MUST ALWAYS BE CALLED!!!!!!!!!!
  EVP_CIPHER_CTX_free(ctx);
  return ciphertext_len;
}
int decrypt(/*Think about what you need!*/)
{
 //do stuff
}