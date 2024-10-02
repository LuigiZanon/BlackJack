import java.util.Scanner;

public class BlackJack {


    public static void roundMenu(){
        System.out.println("Choose one of the following:");
        System.out.println("[1]-Double");
        System.out.println("[2]-Hit");
        System.out.println("[3]-Stand");
        System.out.println("[4]-Surrender (end game)\n");
    }

    public static void dealCard(Deck playdDeck, Hand hand){
        hand.addCard(playdDeck.dealCard());
    }

    public static void print_hands(Hand dealer, Hand player){
        System.out.println("\tDealer hand:");
        System.out.println(dealer);
        System.out.println("\n\n\tYour hand:");
        System.out.println(player);
    }

    public static void StartRound(Hand dealer,Hand player, Deck playDeck){
        playDeck.rearrangeDeck();
        dealer.clearCards();
        player.clearCards();
        dealCard(playDeck, dealer);
        dealCard(playDeck, player);
        dealCard(playDeck, player);
    }

    public static int win(Hand dealer, Hand player){
        //0-> player wins
        //1-> tie
        //2-> dealer wins 
        if (player.HasBlackJack() && dealer.HasBlackJack()) {
            return 1;
        }else if(player.above() && dealer.above()){
            return 1;
        }else if(player.HasBlackJack()) {
            return 0;
        }else if (dealer.HasBlackJack()){
            return 2;
        }else if(dealer.above() && !player.above()){
            return 0;
        }else if (player.above() && !dealer.above()) {
            return 2;
        }else if (player.getHandValue() > dealer.getHandValue()) {
            return 0;
        }else if (dealer.getHandValue() == player.getHandValue()) {
            return 1;
        }else{
            return 2;
        }
    }

    public static int bet(int playerMoney){
        Scanner input = new Scanner(System.in);
        int op;
        do { 
            System.out.println("How much you wanna bet?");
            op = input.nextInt();
            if (op > playerMoney) {
                System.out.println("You don't have enough money");
            }
        } while (op > playerMoney);

        return op;
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void logo(){
        System.out.println(AnsiColors.GREEN + "\r\n" + //
                        "  _         _   _                                           _     _ _             \r\n" + //
                        " | |    ___| |_( )___    __ _  ___     __ _  __ _ _ __ ___ | |__ | (_)_ __   __ _ \r\n" + //
                        " | |   / _ \\ __|// __|  / _` |/ _ \\   / _` |/ _` | '_ ` _ \\| '_ \\| | | '_ \\ / _` |\r\n" + //
                        " | |__|  __/ |_  \\__ \\ | (_| | (_) | | (_| | (_| | | | | | | |_) | | | | | | (_| |\r\n" + //
                        " |_____\\___|\\__| |___/  \\__, |\\___/   \\__, |\\__,_|_| |_| |_|_.__/|_|_|_| |_|\\__, |\r\n" + //
                        "                        |___/         |___/                                 |___/ \r\n" + //
                        "" + AnsiColors.RESET);
    }

    public static void wait_time(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        
        Deck playDeck = new Deck();
        Hand player = new Hand();
        Hand dealer = new Hand();
        int playerMoney = 0;
        int betAmount = 0;
        boolean dealerTurn = false;
        boolean doubled = false;
        int op =0;
        
        System.out.println(AnsiColors.WHITE_BACKGROUND + AnsiColors.BLACK + "-----Welcome to BlackJack!-----"+ AnsiColors.RESET);
        logo();

        do {
            
            if(playerMoney == 0){
                System.out.println("How much money do you have?");
                    playerMoney = input.nextInt();
            }

            dealerTurn = false;
            doubled = false;

            betAmount = bet(playerMoney);
            playerMoney -= betAmount; 

            StartRound(dealer, player, playDeck);

            while(dealerTurn == false){
                if(player.HasBlackJack()){
                    dealerTurn = true;

                }else{
                    clearScreen();
                    System.out.println("Money: $"+playerMoney+ " Bet amount: $"+betAmount);
                    print_hands(dealer, player);
                    roundMenu();
                    op = input.nextInt();
                    switch(op){
                        case 1 -> {
                            if(playerMoney >= betAmount){
                                dealCard(playDeck, player);
                                playerMoney -= betAmount;
                                betAmount = betAmount*2;
                                doubled = true;
                                dealerTurn = true;
                            }else{
                                System.out.println(AnsiColors.RED + "You don't have enough money!"+ AnsiColors.RESET);
                                wait_time(1500);
                            }
                        }
                        case 2 -> dealCard(playDeck, player);
                        
                        case 3 -> {
                            dealerTurn = true;
                        }
                        
                        case 4 -> {
                            System.exit(1);
                        }
                    }

                    if(player.above() == true){
                        System.out.println(AnsiColors.RED_BOLD + "You busted!"+ AnsiColors.RESET);
                        dealerTurn = true;
                    }
                }
            }

            if(dealerTurn == true){
                while(dealer.getHandValue() <= 17){
                    dealer.addCard(playDeck.dealCard());
                }
            }
            clearScreen();
            switch (win(dealer, player)) {
                case 0 -> {
                    System.out.println(AnsiColors.GREEN + "\r\n" + //
                            " __     __                               _ \r\n" + //
                            " \\ \\   / /                              | |\r\n" + //
                            "  \\ \\_/ /__  _   _  __      _____  _ __ | |\r\n" + //
                            "   \\   / _ \\| | | | \\ \\ /\\ / / _ \\| '_ \\| |\r\n" + //
                            "    | | (_) | |_| |  \\ V  V / (_) | | | |_|\r\n" + //
                            "    |_|\\___/ \\__,_|   \\_/\\_/ \\___/|_| |_(_)\r\n" + //
                            "                                           \r\n" + //
                            "                                           \r\n" + //
                            ""+ AnsiColors.RESET);
                    playerMoney += (betAmount*2);
                }

                case 1 -> {
                    System.out.println(AnsiColors.YELLOW_BOLD_BRIGHT + "\r\n" + //
                            "  _______ _____ ______ _ \r\n" + //
                            " |__   __|_   _|  ____| |\r\n" + //
                            "    | |    | | | |__  | |\r\n" + //
                            "    | |    | | |  __| | |\r\n" + //
                            "    | |   _| |_| |____|_|\r\n" + //
                            "    |_|  |_____|______(_)\r\n" + //
                            "                         \r\n" + //
                            "                         \r\n" + //
                            ""+ AnsiColors.RESET);
                    playerMoney += betAmount;
                }

                case 2 -> {
                    System.out.println(AnsiColors.RED_BOLD_BRIGHT +"\r\n" + //
                            "  _____             _                      _           _ \r\n" + //
                            " |  __ \\           | |                    (_)         | |\r\n" + //
                            " | |  | | ___  __ _| | ___ _ __  __      ___ _ __  ___| |\r\n" + //
                            " | |  | |/ _ \\/ _` | |/ _ \\ '__| \\ \\ /\\ / / | '_ \\/ __| |\r\n" + //
                            " | |__| |  __/ (_| | |  __/ |     \\ V  V /| | | | \\__ \\_|\r\n" + //
                            " |_____/ \\___|\\__,_|_|\\___|_|      \\_/\\_/ |_|_| |_|___(_)\r\n" + //
                            "                                                         \r\n" + //
                            "                                                         \r\n" + //
                            ""+ AnsiColors.RESET);
                    if(doubled == true){
                        playerMoney += (betAmount/2);
                    }
                }

                default -> throw new AssertionError();
                }

                print_hands(dealer, player);

                System.out.println("You have a total of $" + playerMoney);
                System.out.println("Wanna play another round?\n[1]-yes\t[2]-no\n");
                    op = input.nextInt();

                if(op == 2){
                    break;
                }

            if(playerMoney == 0){
                System.out.println("You lost all your money :|\n GO AWAY ");
            }
        } while (op != 4);


        input.close();
    }
}