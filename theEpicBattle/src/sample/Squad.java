package sample;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lushi on 21.11.2016.
 */
public class Squad {    //отряд
    private String name;//название отряда
    private ArrayList<Warrior> warrior;//сп и соквоинов вотряде

    public Warrior getRandomWarrior() { //получение случайного воина из отряда к чему эти комментарии? разве из названия метода непонятно, что он делает?
        Random r = new Random();
        int ind, n = warrior.size();
        if (hasAliveWarriors())
            while (true) {
                ind = r.nextInt(n);
                if (warrior.get(ind).isAlive()) {
                    return warrior.get(ind);
                }
            }
        else
            return null;
    }

    public void createSquad(ObservableList<String> squad, String nameSquad) { //создание отряда из списка
        warrior = new ArrayList<Warrior>();
        name = nameSquad;
        Random r = new Random();
        for (int i = 0; i < squad.size(); i++) {
            if (squad.get(i).equals("Берсерк"))
                warrior.add(new Berserk());
            else if (squad.get(i).equals("Лучник"))
                warrior.add(new Archer());
            else if (squad.get(i).equals("Защитник"))
                warrior.add(new Defender());
            else if (squad.get(i).equals("Викинг"))
                warrior.add(new Viking());

            warrior.get(i).setSquadName(nameSquad);
            warrior.get(i).setName("w_" + r.nextInt(100)); //зачем эта w? на мой взгляд, просто мусор в логах
        }
    }

    public boolean hasAliveWarriors() { //проверка есть ли в отряде живые воины
        for (int i = 0; i < warrior.size(); i++) { //foreach смотрелся бы лучше
            if (warrior.get(i).isAlive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {  //получить название отряда
        return name;
    }

    @Override
    public Squad clone() {  //получить копию отряда
        Squad sq = new Squad();
        int n = sq.warrior.size();
        sq.warrior = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sq.warrior.add(this.warrior.get(i).clone());
        }
        return sq;
    }
}
