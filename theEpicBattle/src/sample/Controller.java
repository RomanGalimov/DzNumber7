package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


public class Controller {
    //список элементов управления
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button buttonBattle;
    @FXML
    private Button removeButton1;
    @FXML
    private Button removeButton2;
    @FXML
    private Button infoButton1;
    @FXML
    private Button infoButton2;
    @FXML
    private ComboBox<String> comboBox1;
    @FXML
    private ComboBox<String> comboBox2;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listView2;
    @FXML
    private TextArea textArea;

    //два списка воинов
    ObservableList<String> listSquad1 = FXCollections.observableArrayList();
    ObservableList<String> listSquad2 = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList(    //список доступных видов воинов
                "Берсерк",
                "Лучник",
                "Викинг",
                "Защитник"
        );
        comboBox1.setItems(list);   //инициализация 2х полей данными
        comboBox2.setItems(list);   //о допустимых ви дах воинов

        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            //Событие нажатия на кнопку "Добавить в первый отряд"
            @Override
            public void handle(MouseEvent mouseEvent) {
                String getValueComboBox = comboBox1.getValue();
                try {
                    if (getValueComboBox.equals("Берсерк"))
                        listSquad1.add("Берсерк");
                    else if (getValueComboBox.equals("Лучник"))
                        listSquad1.add("Лучник");
                    else if (getValueComboBox.equals("Викинг"))
                        listSquad1.add("Викинг");
                    else if (getValueComboBox.equals("Защитник"))
                        listSquad1.add("Защитник");
                    listView.setItems(listSquad1);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });

        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            //событие нажатия на кнопку "добавить во второй отряд"
            @Override
            public void handle(MouseEvent mouseEvent) {
                String getValueComboBox = comboBox2.getValue();
                try {
                    if (getValueComboBox.equals("Берсерк"))
                        listSquad2.add("Берсерк");
                    else if (getValueComboBox.equals("Лучник"))
                        listSquad2.add("Лучник");
                    else if (getValueComboBox.equals("Викинг"))
                        listSquad2.add("Викинг");
                    else if (getValueComboBox.equals("Защитник"))
                        listSquad2.add("Защитник");
                    listView2.setItems(listSquad2);
                } catch (Exception e) {
                    e.getMessage();
                }

            }
        });

        buttonBattle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    //нажатие на кнопку "Начать бой"
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        textArea.clear();
                        Squad squad1 = new Squad();
                        Squad squad2 = new Squad();

                        squad1.createSquad(listSquad1, textField1.getText());
                        squad2.createSquad(listSquad2, textField2.getText());

                        Warrior w1, w2;
                        while (squad1.hasAliveWarriors() && squad2.hasAliveWarriors()) {
                            w2 = squad2.getRandomWarrior();
                            w1 = squad1.getRandomWarrior();

                            textArea.appendText(w1.toString() + " атакует " + w2.toString() + "\r\n");
                            textArea.appendText(w2.toString() + " HP: " + w2.takeDamage(w1.attack()) + "\r\n");


                            if (!squad2.hasAliveWarriors()) {
                                textArea.appendText("Первая команда победила!\r\n");
                                break;
                            }
                            w1 = squad1.getRandomWarrior();
                            w2 = squad2.getRandomWarrior();

                            textArea.appendText(w2.toString() + " атакует " + w1.toString() + "\r\n");
                            textArea.appendText(w1.toString() + " HP: " + w1.takeDamage(w2.attack()) + "\r\n");

                            if (!squad1.hasAliveWarriors()) {
                                textArea.appendText("Вторая команда победила!\r\n");
                                break;
                            }
                        }
                    }
                }
        );

        removeButton1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            //нажатие кнопки "Удалить воина из первого отряда"
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedId = listView.getSelectionModel().getSelectedIndex();
                if (selectedId != -1) {
                    String itemToRemove = listView.getSelectionModel().getSelectedItems().toString();
                    listView.getItems().remove(selectedId);
                    listSquad1.remove(itemToRemove);
                }

            }
        });

        removeButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            //нажатие на кнопку "Удалить извторого отряда"
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedId = listView2.getSelectionModel().getSelectedIndex();
                if (selectedId != -1) {
                    String itemToRemove = listView2.getSelectionModel().getSelectedItems().toString();
                    listView2.getItems().remove(selectedId);
                    listSquad2.remove(itemToRemove);
                }
            }
        });

        infoButton1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            //нажатие на кнопку "информация о воине из первого отряда"
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedId = listView.getSelectionModel().getSelectedIndex();
                if (selectedId != -1) {
                    String selectedItem = listView.getSelectionModel().getSelectedItem();
                    if (selectedItem.equals("Берсерк"))
                        ShowAlert(selectedItem, new Berserk().getInfo());
                    else if (selectedItem.equals("Лучник"))
                        ShowAlert(selectedItem, new Archer().getInfo());
                    else if (selectedItem.equals("Защитник"))
                        ShowAlert(selectedItem, new Defender().getInfo());
                    else if (selectedItem.equals("Викинг"))
                        ShowAlert(selectedItem, new Viking().getInfo());
                }
            }
        });

        infoButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            //инофрмация о воине из второго отряда
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selectedId = listView2.getSelectionModel().getSelectedIndex();
                if (selectedId != -1) {
                    String selectedItem = listView2.getSelectionModel().getSelectedItem();
                    if (selectedItem.equals("Берсерк"))
                        ShowAlert(selectedItem, new Berserk().getInfo());
                    else if (selectedItem.equals("Лучник"))
                        ShowAlert(selectedItem, new Archer().getInfo());
                    else if (selectedItem.equals("Защитник"))
                        ShowAlert(selectedItem, new Defender().getInfo());
                    else if (selectedItem.equals("Викинг"))
                        ShowAlert(selectedItem, new Viking().getInfo());
                }
            }
        });
    }

    public void ShowAlert(String classWarrior, String infoAboutWarrior) { //метод для отображения уведоблений
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(classWarrior);
        alert.setHeaderText(infoAboutWarrior);
        alert.showAndWait();
    }

}
