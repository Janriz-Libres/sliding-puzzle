public class Naive {

    public static void main(String[] args) {
        String[] configs = { "12364-785", "12368457-", "15-328467", "57814623-", "357248-61" };

        for (String config : configs)
            System.out.println(isSolvable(config) ? "Solvable." : "Not Solvable.");
    }

    static boolean isSolvable(String config) {
        int inaptPairs = 0;

        for (int i = 0; i < config.length() - 1; i++) {
            String s1 = String.valueOf(config.charAt(i));
            
            for (int j = i + 1; j < config.length(); j++) {
                String s2 = String.valueOf(config.charAt(j));

                try {
                    if (Integer.parseInt(s1) > Integer.parseInt(s2))
                        inaptPairs++;
                } catch (NumberFormatException nfe) {
                    continue;
                }
            }
        }

        System.out.println(inaptPairs);
        return inaptPairs % 2 == 0;
    }
}