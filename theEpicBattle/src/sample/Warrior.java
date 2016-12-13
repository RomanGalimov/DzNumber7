package sample;

/**
 * Created by lushi on 12.12.2016.
 */
public interface Warrior {  //интерфейс воина
    int attack();           //атака

    String takeDamage(int damage); //получение урона

    boolean isAlive();      //проверка жив ли

    void setSquadName(String name); //указать отряд

    void setName(String name);  //указать имя воина

    String getInfo();   //получить информацию о воине

    String toString();  //вывод доп. информации о воине

    Warrior clone();    //клонировать воина
}