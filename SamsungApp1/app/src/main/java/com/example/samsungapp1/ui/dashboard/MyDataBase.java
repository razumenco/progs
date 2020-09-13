package com.example.samsungapp1.ui.dashboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.Locale;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Countries";
    public static final String KEY_COUNTRY = "country";
    public static final String KEY_CAPITAL = "capital";

    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_COUNTRY + " TEXT, " + KEY_CAPITAL + " TEXT)");
        fillWithCountries(sqLiteDatabase);
    }

    private void fillWithCountries(SQLiteDatabase sqLiteDatabase) {
        String sql = String.format(Locale.US, "INSERT INTO %s (" + KEY_COUNTRY + ", " + KEY_CAPITAL + ") " + "VALUES" +
                "(\"Австрия\", \"Вена\")," +
                "(\"Азербайджан\", \"Баку\")," +
                "(\"Албания\", \"Тирана\")," +
                "(\"Алжир\", \"Алжир\")," +
                "(\"Ангола\", \"Луанда\")," +
                "(\"Андорра\", \"Андорра-ла-Велья\")," +
                "(\"Антигуа\", \"и\")," +
                "(\"Аргентина\", \"Буэнос-Айрес\")," +
                "(\"Армения\", \"Ереван\")," +
                "(\"Афганистан\", \"Кабул\")," +
                "(\"Багамы\", \"Нассау\")," +
                "(\"Бангладеш\", \"Дакка\")," +
                "(\"Барбадос\", \"Бриджтаун\")," +
                "(\"Бахрейн\", \"Манама\")," +
                "(\"Беларусь\", \"Минск\")," +
                "(\"Белиз\", \"Бельмопан\")," +
                "(\"Бельгия\", \"Брюссель\")," +
                "(\"Бенин\", \"Порто-Ново\")," +
                "(\"Болгария\", \"София\")," +
                "(\"Боливия\", \"Сукре\")," +
                "(\"Босния и Герцеговина\", \"Сараево\")," +
                "(\"Ботсвана\", \"Габороне\")," +
                "(\"Бразилия\", \"Бразилиа\")," +
                "(\"Бруней\", \"Бандар-Сери-Багаван\")," +
                "(\"БуркинаФасо\", \"Уагадугу\")," +
                "(\"Бурунди\", \"Бужумбура\")," +
                "(\"Бутан\", \"Тхимпху\")," +
                "(\"Вануату\", \"Порт-Вила\")," +
                "(\"Ватикан\", \"Ватикан\")," +
                "(\"Великобритания\", \"Лондон\")," +
                "(\"Венгрия\", \"Будапешт\")," +
                "(\"Венесуэла\", \"Каракас\")," +
                "(\"Восточный\", \"Тимор\")," +
                "(\"Вьетнам\", \"Ханой\")," +
                "(\"Габон\", \"Либревиль\")," +
                "(\"Гаити\", \"Порт-о-Пренс\")," +
                "(\"Гайана\", \"Джорджтаун\")," +
                "(\"Гамбия\", \"Банжул\")," +
                "(\"Гана\", \"Аккра\")," +
                "(\"Гватемала\", \"Гватемала\")," +
                "(\"Гвинея\", \"Конакри\")," +
                "(\"Гвинея-Бисау\", \"Бисау\")," +
                "(\"Германия\", \"Берлин\")," +
                "(\"Гондурас\", \"Тегусигальпа\")," +
                "(\"Гренада\", \"Сент-Джорджес\")," +
                "(\"Греция\", \"Афины\")," +
                "(\"Грузия\", \"Тбилиси\")," +
                "(\"Дания\", \"Копенгаген\")," +
                "(\"Джибути\", \"Джибути\")," +
                "(\"Доминика\", \"Розо\")," +
                "(\"Доминиканская Республика\", \"Санто-Доминго\")," +
                "(\"Египет\", \"Каир\")," +
                "(\"Замбия\", \"Лусака\")," +
                "(\"Зимбабве\", \"Хараре\")," +
                "(\"Израиль\", \"Иерусалим\")," +
                "(\"Индия\", \"Нью-Дели\")," +
                "(\"Индонезия\", \"Джакарта\")," +
                "(\"Иордания\", \"Амман\")," +
                "(\"Ирак\", \"Багдад\")," +
                "(\"Иран\", \"Тегеран\")," +
                "(\"Ирландия\", \"Дублин\")," +
                "(\"Исландия\", \"Рейкьявик\")," +
                "(\"Испания\", \"Мадрид\")," +
                "(\"Италия\", \"Рим\")," +
                "(\"Йемен\", \"Сана\")," +
                "(\"Кабо-Верде\", \"Прая\")," +
                "(\"Казахстан\", \"Астана\")," +
                "(\"Камбоджа\", \"Пномпень\")," +
                "(\"Камерун\", \"Яунде\")," +
                "(\"Канада\", \"Оттава\")," +
                "(\"Катар\", \"Доха\")," +
                "(\"Кения\", \"Найроби\")," +
                "(\"Кипр\", \"Никосия\")," +
                "(\"Киргизия\", \"Бишкек\")," +
                "(\"Кирибати\", \"Южная\")," +
                "(\"Китай\", \"Пекин\")," +
                "(\"Колумбия\", \"Санта-фе-де-Богота\")," +
                "(\"Коморы\", \"Морони\")," +
                "(\"Конго,демократическая республика\", \"Киншаса\")," +
                "(\"Конго,республика\", \"Браззавиль\")," +
                "(\"Коста-Рика\", \"Сан-Хосе\")," +
                "(\"Кот-д’Ивуар\", \"Ямусукро\")," +
                "(\"Куба\", \"Гавана\")," +
                "(\"Кувейт\", \"Эль-Кувейт\")," +
                "(\"Лаос\", \"Вьентьян\")," +
                "(\"Латвия\", \"Рига\")," +
                "(\"Лесото\", \"Масеру\")," +
                "(\"Либерия\", \"Монровия\")," +
                "(\"Ливан\", \"Бейрут\")," +
                "(\"Ливия\", \"Триполи\")," +
                "(\"Литва\", \"Вильнюс\")," +
                "(\"Лихтенштейн\", \"Вадуц\")," +
                "(\"Люксембург\", \"Люксембург\")," +
                "(\"Маврикий\", \"Порт-Луи\")," +
                "(\"Мавритания\", \"Нуакшот\")," +
                "(\"Мадагаскар\", \"Антананариву\")," +
                "(\"Македония\", \"Скопье\")," +
                "(\"Малави\", \"Лилонгве\")," +
                "(\"Малайзия\", \"Куала-Лумпур\")," +
                "(\"Мали\", \"Бамако\")," +
                "(\"Мальдивы\", \"Мале\")," +
                "(\"Мальта\", \"Валлетта\")," +
                "(\"Марокко\", \"Рабат\")," +
                "(\"Маршалловы Острова\", \"Маджуро\")," +
                "(\"Мексика\", \"Мехико\")," +
                "(\"Мозамбик\", \"Мапуту\")," +
                "(\"Молдавия\", \"Кишинев\")," +
                "(\"Монако\", \"Монако\")," +
                "(\"Монголия\", \"Улан-Батор\")," +
                "(\"Мьянма\", \"Найпьидо\")," +
                "(\"Намибия\", \"Виндхук\")," +
                "(\"Непал\", \"Катманду\")," +
                "(\"Нигер\", \"Ниамей\")," +
                "(\"Нигерия\", \"Абуджа\")," +
                "(\"Нидерланды\", \"Амстердам\")," +
                "(\"Никарагуа\", \"Манагуа\")," +
                "(\"Новая\", \"Зеландия\")," +
                "(\"Норвегия\", \"Осло\")," +
                "(\"Объединенные Арабские Эмираты\", \"Абу-Даби\")," +
                "(\"Оман\", \"Маскат\")," +
                "(\"Пакистан\", \"Исламабад\")," +
                "(\"Палау\", \"Мелекеок\")," +
                "(\"Панама\", \"Панама\")," +
                "(\"Папуа-Новая\", \"Гвинея\")," +
                "(\"Парагвай\", \"Асунсьон\")," +
                "(\"Перу\", \"Лима\")," +
                "(\"Польша\", \"Варшава\")," +
                "(\"Португалия\", \"Лиссабон\")," +
                "(\"Россия\", \"Москва\")," +
                "(\"Руанда\", \"Кигали\")," +
                "(\"Румыния\", \"Бухарест\")," +
                "(\"Сальвадор\", \"Сан-Сальвадор\")," +
                "(\"Самоа\", \"Апиа\")," +
                "(\"Сан-Марино\", \"Сан-Марино\")," +
                "(\"СаудовскаяАравия\", \"Эр-Рияд\")," +
                "(\"Свазиленд\", \"Мбабане\")," +
                "(\"СевернаяКорея\", \"Пхеньян\")," +
                "(\"Сейшелы\", \"Виктория\")," +
                "(\"Сенегал\", \"Дакар\")," +
                "(\"Сент-Китс\", \"и\")," +
                "(\"Сент-Люсия\", \"Кастри\")," +
                "(\"Сербия\", \"Белград\")," +
                "(\"Сингапур\", \"Сингапур\")," +
                "(\"Сирия\", \"Дамаск\")," +
                "(\"Словакия\", \"Братислава\")," +
                "(\"Словения\", \"Любляна\")," +
                "(\"Соединенные Штаты Америки\", \"Вашингтон\")," +
                "(\"Соломоновы Острова\", \"Хониара\")," +
                "(\"Сомали\", \"Могадишо\")," +
                "(\"Судан\", \"Хартум\")," +
                "(\"Суринам\", \"Парамарибо\")," +
                "(\"Сьерра-Леоне\", \"Фритаун\")," +
                "(\"Таджикистан\", \"Душанбе\")," +
                "(\"Таиланд\", \"Бангкок\")," +
                "(\"Танзания\", \"Додома\")," +
                "(\"Того\", \"Ломе\")," +
                "(\"Тонга\", \"Нукуалофа\")," +
                "(\"Тринидад и Тобаго\", \"Порт-оф-Спейн\")," +
                "(\"Тувалу\", \"Фунафути\")," +
                "(\"Тунис\", \"Тунис\")," +
                "(\"Туркмения\", \"Ашхабад\")," +
                "(\"Турция\", \"Анкара\")," +
                "(\"Уганда\", \"Кампала\")," +
                "(\"Узбекистан\", \"Ташкент\")," +
                "(\"Украина\", \"Киев\")," +
                "(\"Уругвай\", \"Монтевидео\")," +
                "(\"Федеративные штаты Микронезии\", \"Паликир\")," +
                "(\"Фиджи\", \"Сува\")," +
                "(\"Филиппины\", \"Манила\")," +
                "(\"Финляндия\", \"Хельсинки\")," +
                "(\"Франция\", \"Париж\")," +
                "(\"Хорватия\", \"Загреб\")," +
                "(\"Центрально-Африканская Республика\", \"Банги\")," +
                "(\"Чад\", \"Нджамена\")," +
                "(\"Черногория\", \"Подгорица\")," +
                "(\"Чехия\", \"Прага\")," +
                "(\"Чили\", \"Сантьяго\")," +
                "(\"Швейцария\", \"Берн\")," +
                "(\"Швеция\", \"Стокгольм\")," +
                "(\"Шри-Ланка\", \"Коломбо\")," +
                "(\"Эквадор\", \"Кито\")," +
                "(\"Экваториальная\", \"Гвинея\")," +
                "(\"Эритрея\", \"Асмэра\")," +
                "(\"Эстония\", \"Таллин\")," +
                "(\"Эфиопия\", \"Аддис-Абеба\")," +
                "(\"Южная Корея\", \"Сеул\")," +
                "(\"Южно-Африканская Республика\", \"Претория\")," +
                "(\"Ямайка\", \"Кингстон\")," +
                "(\"Япония\", \"Токио\");", TABLE_NAME);

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}