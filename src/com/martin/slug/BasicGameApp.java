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
        settings.setTitle("Martin Slug");
        settings.setVersion("0.1");
    }

    private Entity player;

    @Override
    protected void initGame(){
        player = Entities.builder()
                .at(300,300)
                .viewFromNode(new Rectangle(25,25,Color.RED))
                .buildAndAttach(getGameWorld());
    }

    @Override
    protected void initInput(){
        Input input = getInput();

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction(){
                player.translateX(5);
            }

        }, KeyCode.D);

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.translateX(-5); // move left 5 pixels
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.translateY(-5); // move up 5 pixels
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.translateY(5); // move down 5 pixels
            }
        }, KeyCode.S);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
