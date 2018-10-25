package FB;

/**
 phone interview
 Given a function which reads from network stream up to 4k at a time,
 give a function which can satisfy requests for arbitrary number of characters (toRead)

 Due to network latency, if the read4K method returns a value < 4k, it does not
 necessarily mean that we reach the end of file(EOF) ,in this case ,you should continue to read
 the file until you reach toRead or EOF
 */
public class Read4K {

    final char EOF = '\0';

    private int read4K(char[] buff) { return 0; }

    public int read(char[] buff, int toRead) {

        int size = 0;

        while(size < toRead) {

            char[] buff4K = new char[4096];

            int next4K = read4K(buff4K);

            int i = 0;

            while(i < next4K && i + size < toRead) {

                if(buff4K[i] != EOF) {

                    buff[i + size] = buff4K[i];

                    i++;

                } else return size + i;

            }

            size += i;

        }

        return size;


    }

    public int readBuff(char[] buff, int toRead) {

        int size = 0;

        while(size < toRead) {

            char[] buff4K = new char[4096];

            int size4K = 0, EOF = -1;

            while(size4K < 4096 && EOF == -1) { //fill buff4K up

                int buff_in = read4K(buff4K);

                for(int i = size4K; i < buff_in + size4K; i++) { //look for EOF in current read

                    if(buff4K[i] == this.EOF) { //EOF found

                        EOF = i;

                        break;

                    }

                }

                size4K += buff_in;

            }

            int end = size + Math.min(size4K, EOF);

            for(int i = size; i < end && i < toRead; i++, size++) {  //write buff4K into buffer

                buff[i] = buff4K[i - size];

            }

        }

        return size;


    }

}
