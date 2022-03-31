import java.util.Scanner;

public class Input
    {
        /**
         * permet de faire une entree pour par la suite avoir des deplacements.
         * @return l'entree prise par l'utilisateur
         */
        public static String get_Input()
        {
            Scanner object = new Scanner(System.in);
            System.out.println("Please enter => \nz : up s : down : d : right q : left\ne : escape ");
            String input = object.nextLine();
            if (islegal(input))
                get_Input();
            return input;
        }

        /**
         * verifie si l'entree est respecte
         * @param input l'entree de l'utilisateur
         * @return vrai si entree respecte , faux sinon
         */
        private static boolean islegal(String input)
        {
            String legitinput[] = {"z","s","q","d","e"};
            for(int i = 0; i < legitinput.length ; i++)
                if (legitinput[i].equals(input))
                    return false;
            return true;
        }
    }


