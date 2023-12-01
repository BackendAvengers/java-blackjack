package blackjack.view.input;

import camp.nextstep.edu.missionutils.Console;

public abstract class ConsoleInput {

    protected String readLine() {
        return Console.readLine();
    }

    protected void close() {
        Console.close();
    }


}
