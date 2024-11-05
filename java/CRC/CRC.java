public class CRC {
    private final int[] polynomial;
    private final int polynomialDegree;

    // Constructor to initialize CRC with the given polynomial
    public CRC(String polynomialBinary) {
        this.polynomial = binaryToIntArray(polynomialBinary);
        this.polynomialDegree = polynomial.length - 1;
    }

    // Convert a binary string to an integer array
    private int[] binaryToIntArray(String binary) {
        int[] array = new int[binary.length()];
        for (int i = 0; i < binary.length(); i++) {
            array[i] = binary.charAt(i) - '0'; // Convert char to int
        }
        return array;
    }

    // Perform CRC calculation on the given data
    public String calculateCRC(String data) {
        int[] dataBits = binaryToIntArray(data);
        int[] augmentedData = new int[dataBits.length + polynomialDegree];
        
        // Copy data bits into augmented data
        System.arraycopy(dataBits, 0, augmentedData, 0, dataBits.length);

        // Perform the division
        for (int i = 0; i < dataBits.length; i++) {
            if (augmentedData[i] == 1) {
                for (int j = 0; j < polynomial.length; j++) {
                    augmentedData[i + j] ^= polynomial[j]; // XOR operation
                }
            }
        }

        // The CRC code is the last 'n' bits
        StringBuilder crc = new StringBuilder();
        for (int i = augmentedData.length - polynomialDegree; i < augmentedData.length; i++) {
            crc.append(augmentedData[i]);
        }
        
        return crc.toString();
    }

    // Validate the received data with CRC
    public boolean validateCRC(String dataWithCRC) {
        int[] dataBits = binaryToIntArray(dataWithCRC);
        int[] augmentedData = new int[dataBits.length + polynomialDegree];
        
        // Copy data bits into augmented data
        System.arraycopy(dataBits, 0, augmentedData, 0, dataBits.length);

        // Perform the division
        for (int i = 0; i < dataBits.length; i++) {
            if (augmentedData[i] == 1) {
                for (int j = 0; j < polynomial.length; j++) {
                    augmentedData[i + j] ^= polynomial[j]; // XOR operation
                }
            }
        }

        // If the last 'n' bits are all zero, then it is valid
        for (int i = augmentedData.length - polynomialDegree; i < augmentedData.length; i++) {
            if (augmentedData[i] == 1) {
                return false; // There is an error
            }
        }
        
        return true; // No error detected
    }

    public static void main(String[] args) {
        CRC crc = new CRC("1101"); // Example polynomial (x^3 + x^2 + 1)
        
        String data = "1011001"; // Example data
        String crcValue = crc.calculateCRC(data);
        System.out.println("Data: " + data);
        System.out.println("CRC: " + crcValue);
        
        // Transmitting data with CRC
        String transmittedData = data + crcValue;
        System.out.println("Transmitted Data: " + transmittedData);

        // Validating the received data
        boolean isValid = crc.validateCRC(transmittedData);
        System.out.println("Is the received data valid? " + isValid);
        
        // Simulating an error in the transmitted data
        transmittedData = transmittedData.substring(0, transmittedData.length() - 1) + '1'; // Flip last bit
        System.out.println("Corrupted Data: " + transmittedData);
        
        isValid = crc.validateCRC(transmittedData);
        System.out.println("Is the corrupted data valid? " + isValid);
    }
}
