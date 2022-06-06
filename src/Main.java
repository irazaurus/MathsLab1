public class Main {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();

        int number = inputHandler.askNumber();
        int position = inputHandler.askPosition();

        GraphicHandler graphic = new GraphicHandler(number, position);
        graphic.paint(graphic.getGraphics());
    }

}
