package org.company;

import org.javatuples.Pair;
import java.util.HashMap;

public class Container implements IContainer {
    private static Container instance;
    // Пара строк "from" и "to" уникальна организуем хранение в структуре данных HashMap
    private HashMap<Pair<String, String>, Float> pairMap = new HashMap<>();
    private Float valueG = null;
    private Float valueTemp = null;
    private static int count = 0;

    private Container(){
        // Проверка Singleton
        System.out.println("Singleton Container created!");
    }

    // Ленивая реализация Singleton c быстрой инициализацией
    public static Container getInstance() {
        if (instance == null) {
            synchronized (Container.class) {
                if (instance == null) {
                    instance = new Container();
                }
            }
        }
        return instance;
    }
    @Override
    public Float Find(String KeyA, String KeyB) {
        count = 0;
        valueG =  0f;

        return Calc(KeyA, KeyB);
    }

    public Float Calc(String KeyA, String KeyB) {
        Float result;
        result = pairMap.get(new Pair<>(KeyA, KeyB));
        if(result != null) {
            return result;
        }
        else {
            if(count < pairMap.size()) {
                pairMap.forEach((k, v) -> {
                    if (k.getValue0().equals(KeyA)) {
                        // Искать до тех пор пока цепочка преобразования найдена или все варианты перебрали;
                        if (k.getValue1().equals(KeyB)) return;
                        valueG = v;
                        // Подсчёт числа вложений
                        count++;
                        valueTemp = Calc(k.getValue1(), KeyB);
                        if (valueTemp != null) {
                            valueG = valueG * valueTemp;
                            return;
                        }
                    }
                });
            }
            else {
                valueG = null;
            }
        }

        return valueG;
    }

    @Override
    public void Add(String KeyA, String KeyB, Float Value) {
        pairMap.put(new Pair<>(KeyA, KeyB), Value);
    }

    @Override
    public void Delete(String KeyA, String KeyB) {
        pairMap.remove(new Pair<>(KeyA, KeyB));
    }

    @Override
    public void Clear() {
        pairMap.clear();
    }

    @Override
    public Integer Size() {
        return  pairMap.size();
    }
}
