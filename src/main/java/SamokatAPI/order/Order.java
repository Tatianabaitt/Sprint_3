package SamokatAPI.order;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;

@Data
public class Order {

    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public static char[] russianAlphabetic = new char[]{'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    public static String[] metroMoscow = new String[]{
            "Авиамоторная", "Академическая", "Александровский сад", "Алексеевская", "Алтуфьево", "Аннино", "Арбатская", "Автозаводская", "Алма-Атинская", "Аэропорт",
            "Бабушкинская", "Багратионовская", "Баррикадная", "Бауманская", "Беговая", "Белорусская", "Беляево", "Бибирево", "Библиотека имени Ленина", "Битцевский парк", "Борисово", "Боровицкая", "Ботанический сад", "Братиславская", "Бульвар Дмитрия Донского", "Бульвар Рокоссовского", "Бульвар Адмирала Ушакова", "Бунинская аллея",
            "ВДНХ", "Варшавская", "Владыкино", "Волгоградский проспект", "Волжская", "Волоколамская", "Воробьёвы горы", "Водный стадион", "Войковская", "Выставочная", "Выхино",
            "Деловой центр", "Дмитровская", "Достоевская", "Дубровка", "Динамо", "Домодедовская",
            "Жулебино", "Зябликово", "Измайловская",
            "Калужская", "Кантемировская", "Каховская", "Каширская", "Киевская", "Киевская", "Китай-город", "Кожуховская", "Комсомольская", "Коньково", "Котельники", "Коломенская", "Красносельская", "Красные Ворота", "Крестьянская застава", "Кропоткинская", "Красногвардейская", "Крылатское", "Кузнецкий Мост", "Кузьминки", "Кунцевская", "Курская", "Кутузовская",
            "Ленинский проспект", "Лермонтовский проспект", "Лесопарковая", "Лубянка", "Люблино",
            "Марксистская", "Марьина роща", "Марьино", "Медведково", "Международная", "Менделеевская", "Митино", "Молодёжная", "Мякинино",
            "Нагатинская", "Нагорная", "Нахимовский проспект", "Новогиреево", "Новокосино", "Новокузнецкая", "Новоясеневская", "Новые Черёмушки",
            "Октябрьская", "Октябрьское поле", "Орехово", "Отрадное", "Охотный Ряд",
            "Павелецкая", "Парк культуры", "Парк Победы", "Партизанская", "Первомайская", "Перово", "Петровско-Разумовская", "Печатники", "Пионерская", "Планерная", "Площадь Ильича", "Площадь Революции", "Полежаевская", "Полянка", "Пражская", "Преображенская площадь", "Пролетарская", "Проспект Вернадского", "Проспект Мира", "Профсоюзная", "Пушкинская", "Пятницкое шоссе",
            "Речной вокзал", "Рижская", "Римская", "Румянцево", "Рязанский проспект",
            "Савёловская", "Саларьево", "Свиблово", "Севастопольская", "Семёновская", "Серпуховская", "Славянский бульвар", "Смоленская", "Сокол", "Сокольники", "Спартак", "Спортивная", "Сретенский бульвар", "Строгино", "Студенческая", "Сухаревская", "Сходненская",
            "Таганская", "Текстильщики", "Тёплый Стан", "Тверская", "Театральная", "Тимирязевская", "Третьяковская", "Тропарёво", "Трубная", "Тульская", "Тургеневская", "Тушинская",
            "Улица 1905 года", "Улица Горчакова", "Улица Скобелевская", "Улица Старокачаловская", "Улица Академика Янгеля", "Университет",
            "Филёвский парк", "Фили", "Царицыно", "Фрунзенская", "Цветной бульвар",
            "Черкизовская", "Чертановская", "Чеховская", "Чистые пруды", "Чкаловская",
            "Шаболовская", "Шипиловская", "Шоссе Энтузиастов",
            "Щёлковская", "Щукинская", "Электрозаводская", "Юго-Западная", "Южная", "Ясенево"};
    public static String[] availableColor = new String[]{"BLACK", "GREY", null};
    public static int count = 0;

    public Order(String firstName, String lastName, String address, String metroStation,
                 String phone, String rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order getRandomWithChooseColor(List<String> color) {
        Random rand = new Random();
        String firstName = RandomStringUtils.random(7, russianAlphabetic);
        String lastName = RandomStringUtils.random(7, russianAlphabetic);
        String address = RandomStringUtils.random(7, russianAlphabetic);
        String metroStation = metroMoscow[rand.nextInt(metroMoscow.length)];
        String phone = RandomStringUtils.randomNumeric(11);
        String rentTime = RandomStringUtils.randomNumeric(4);
        int day = 1 + rand.nextInt(28);
        int month = 1 + rand.nextInt(12);
        int year = 2022 + rand.nextInt(2030 - 2022 + 1);
        String deliveryDate = year + "." + month + "." + day;
        String comment = RandomStringUtils.random(7, russianAlphabetic);
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public static Order getRandom() {
        Random rand = new Random();
        String firstName = RandomStringUtils.random(7, russianAlphabetic);
        String lastName = RandomStringUtils.random(7, russianAlphabetic);
        String address = RandomStringUtils.random(7, russianAlphabetic);
        String metroStation = metroMoscow[rand.nextInt(metroMoscow.length)];
        String phone = RandomStringUtils.randomNumeric(11);
        String rentTime = RandomStringUtils.randomNumeric(4);
        int day = 1 + rand.nextInt(28);
        int month = 1 + rand.nextInt(12);
        int year = 2022 + rand.nextInt(2030 - 2022 + 1);
        String deliveryDate = year + "." + month + "." + day;
        String comment = RandomStringUtils.random(7, russianAlphabetic);
        List<String> color = List.of(availableColor[rand.nextInt(availableColor.length)]);
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
