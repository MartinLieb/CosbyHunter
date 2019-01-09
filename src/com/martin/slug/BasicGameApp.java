package com.martin.slug;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.Map;

public class BasicGameApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("Cosby Hunter");
        settings.setVersion("0.1");
    }

    private Entity player;

    @Override
    protected void initGame(){
        player = Entities.builder()
                .at(100,200)
                .viewFromTexture("gamesprite.png")
                .buildAndAttach(getGameWorld());

        player = Entities.builder()
                .at(700, 300)
                .viewFromTexture("cosbyboss.png")
                .buildAndAttach(getGameWorld());
    }

    @Override
    protected void initInput(){
        Input input = getInput();

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction(){
                player.translateX(5);
                getGameState().increment("pixelsMoved", +5);
            }

        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.translateX(-5); // move left 5 pixels
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.translateY(-5); // move up 5 pixels
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.translateY(5); // move down 5 pixels
                getGameState().increment("pixelsMoved", +5);
            }
        }, KeyCode.S);

    }

    @Override
    protected void initUI() {
        Text textPixels = new Text();
        textPixels.setTranslateX(25); // x = 50
        textPixels.setTranslateY(25); // y = 100

        textPixels.textProperty().bind(getGameState().intProperty("pixelsMoved").asString());

        getGameScene().addUINode(textPixels); // add to the scene graph
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
