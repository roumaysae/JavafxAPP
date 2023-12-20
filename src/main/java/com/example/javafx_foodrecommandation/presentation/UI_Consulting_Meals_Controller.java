package com.example.javafx_foodrecommandation.presentation;

import com.example.javafx_foodrecommandation.Listener;
import com.example.javafx_foodrecommandation.dao.Meal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class UI_Consulting_Meals_Controller implements Initializable {

    @FXML
    private Text IngredientMeal;

    @FXML
    private Text InstructionsMeal;

    @FXML
    private ImageView imageMeal;

    @FXML
    private Label mealCountry;

    @FXML
    private Label nameMeal;

    @FXML
    private GridPane gridpane;

    @FXML
    private ScrollPane scrollPan;
    private Listener listener;
    private List<Meal> mealList = new ArrayList<>();
    private Image image ;


    private List<Meal> getData() {
        List<Meal> meals = new ArrayList<>();
        Meal meal;
        for (int i = 0; i <meals.size(); i++) {
            meal = new Meal();
            meal.setTitle("Lamb Pilaf ");
            meal.setImageMeal("../../resources/com/example/javafx_foodrecommandation/public/meal1Consulting.jpg");
            meals.add(meal);
        }
        return meals;
    }


    private void setChosenMeal(Meal meal){
        nameMeal.setText(meal.getTitle());
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(meal.getImageMeal())));
        imageMeal.setImage(image);
        mealCountry.setText(meal.getCountry());
        IngredientMeal.setText(meal.getIngredient());
        InstructionsMeal.setText(meal.getInstructions());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mealList.addAll(getData());
        if(!mealList.isEmpty()){
            setChosenMeal(mealList.get(0));
           listener = new Listener() {
               @Override
               public void onclikListener(Meal meal) {
                   setChosenMeal(meal);
               }
           };
        }
        int column = 0;
        int row =1;
        try {
            for (int i = 0; i < mealList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/resources/com/example/javafx_foodrecommandation/UI_Card_Meals.fxml"));

                UI_Card_Meals_Controller ui_card_meals = fxmlLoader.getController();
                ui_card_meals.setData(mealList.get(i),listener);

                AnchorPane anchorPane = fxmlLoader.load();

                    if(column==3){
                        column=0;
                        row++;
                    }
                gridpane.add(anchorPane,column++,row);//child + col + row
                //set card grid with
                gridpane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefWidth(Region.USE_PREF_SIZE);
                gridpane.setMaxWidth(Region.USE_COMPUTED_SIZE);
                //set card grid hieght
                gridpane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridpane.setPrefHeight(Region.USE_PREF_SIZE);
                gridpane.setMaxHeight(Region.USE_COMPUTED_SIZE);

                GridPane.setMargin(anchorPane,new Insets(10));
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        }

}

