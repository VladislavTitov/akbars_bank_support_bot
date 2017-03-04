package com.dreamproduction.neural;

import java.util.ArrayList;
import java.util.List;


public class DictionaryList {
    private static final List<String> queryDictionary = new ArrayList<String>() {
        {
            add("карт");
            add("мал");
            add("кредит");
            add("курс");
            add("кабинет");
            add("йти");
            add("онлайн");
            add("регистр");
            add("вклад");
            add("страхов");
            add("завещан");
            add("перевод");
            add("перевести");
            add("филиал");
            add("акци");
            add("золот");
            add("серебр");
            add("бронз");
            add("металл");
        }
    };

    private static final List<String> answers = new ArrayList<String>() {{
        add("Информация о картах здесь: https://www.akbars.ru/individuals/card/");//1
        add("Найти нужную карту: https://www.akbars.ru/individuals/card/service-selection/"); //2
        add("Актуальный курс: https://www.akbars.ru/individuals/exchange/"); //3
        add("Войти в личный кабинет: https://online.akbars.ru/wb/#login"); //4
        add("Зарегистрироваться в личном кабинете: https://online.akbars.ru/wb/#enrollment"); //5
        add("Информация по переводам: https://www.akbars.ru/individuals/transfers/"); //6
        add("Местоположения филиалов: https://www.akbars.ru/about/general/branches/"); //7
        add("Информация об акциях: https://www.akbars.ru/all-action/arhiv/"); //8
        add("Информация о тарифах: https://www.akbars.ru/banks/correspondent/rates/"); //9
        add("Информация о кредитах: https://www.akbars.ru/corporate/credits/"); //10
        add("Кредиты для малого бизнеса: https://www.akbars.ru/corporate/credits/small-business/"); //11
        add("Варианты кредитов: https://www.akbars.ru/individuals/credits/consume/"); //12
        add("Информация по вкладам: https://www.akbars.ru/individuals/deposits/"); //13
        add("Страхование вкладов: https://www.akbars.ru/individuals/deposits/insurance/"); //14
        add("Завещание вкладов: https://www.akbars.ru/individuals/deposits/will-deposit/"); //15
        add("Информация о драгоценных металлах: https://www.akbars.ru/banks/gold/"); //16
        add("Я не распознал ваш запрос. Попробуйте сформулировать вопрос по-другому или обратитесь за помощью к сотруднику банка!"); //17
    }};

    public static List<String> getQueryDictionary() {
        return queryDictionary;
    }

    public static List<String> getAnswers() {
        return answers;
    }
}
