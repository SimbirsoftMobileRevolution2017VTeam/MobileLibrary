package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Denis on 03.10.2017.
 */

public class Library {

    private List<Book> books = new ArrayList<>();

    public void loadLibrary(){
        createFictionData();
    }

    public Book getBookWithIndex(int index) {
        if(books.size() == 0){
            return null;
        }
        if((index < 0) || (index >= books.size())){
            return null;
        }
        return books.get(index);
    }

    private void createFictionData(){
        books.add(new Book("Снежная Королева", "Ханс Андерсен", createDateFromString("2017"), "Верже ИД", "9785906928030", 72, true));
        books.add(new Book("Незнакомка", "Анна Рэй", createDateFromString("2017"), "Альфа-книга", "978-5-9922-2492-4", 314, true));
        books.add(new Book("Портрет с пулей в челюсти", "Ханна Кралль", createDateFromString("2017"), "Corpus", "978-5-17-098823-5", 384, true));
        books.add(new Book("Старик и шляпа", "Игорь Шевчук", createDateFromString("2017"), "Мещерякова ИД", "978-5-00108-134-0", 32, true));
        books.add(new Book("Как я была маленькой", "Вера Желиховская", createDateFromString("2017"), "Речь", "978-5-9268-2583-8", 208, true));
        books.add(new Book("Лесная газета. Осень", "Виталий Бианки", createDateFromString("2017"), "Мещерякова ИД", "978-5-00108-128-9", 96, true));
        books.add(new Book("Исчезнувший мир", "Игорь Акимушкин", createDateFromString("2017"), "Мещерякова ИД", "978-5-00108-095-4", 192, true));
        books.add(new Book("Цена вопроса", "Александра Маринина", createDateFromString("2017"), "Эксмо", "978-5-04-004675-1", 352, true));
        books.add(new Book("В тихом омуте", "Пола Хокинс", createDateFromString("2017"), "АСТ", "978-5-17-104797-9", 384, true));
        books.add(new Book("Шепот в темноте", "Елена Звездная", createDateFromString("2017"), "Эксмо", "978-5-699-96975-3", 352, true));
        books.add(new Book("Июнь", "Дмитрий Быков", createDateFromString("2017"), "Редакция Елены Шубиной", "978-5-17-092368-7", 512, true));
        books.add(new Book("Остров Сокровищ", "Роберт Стивенсон", createDateFromString("2017"), "Лабиринт", "978-5-9287-2526-6", 188, true));
    }

    private Date createDateFromString(String year) {
        DateFormat dateformat = new SimpleDateFormat("yyyy");
        try {
            return dateformat.parse(year);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
