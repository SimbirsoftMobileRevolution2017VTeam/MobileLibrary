package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.model;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Denis on 09.10.2017.
 */

public class LibraryModel implements IBookGetter {

    public List<Book> getBooks() {
        return createFictionData();
    }

    public Book getBookWithId(int id) {
        List<Book> books = createFictionData();
        if(books.size() == 0){
            return null;
        }
        for (Book book:books) {
            if(book.getId() == id){
                return book;
            }
        }
        return null;
    }

    public List<Book> getFilteredFavouriteBooks(){
        List<Book> books = createFictionData();
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()) {
            Book next = iterator.next();
            if(!next.isFavourite()) {
                iterator.remove();
            }
        }
        return books;
    }
    /**
     * Вместо этого метода другой должен работать с БД на сервере
     * In future this method must be changed for request data from DB
     *  */
    private List<Book> createFictionData(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(0, "Снежная Королева", "Ханс Андерсен", createDateFromString("2017"), "Верже ИД", "9785906928030", 72, true, false, false));
        books.add(new Book(1, "Незнакомка", "Анна Рэй", createDateFromString("2017"), "Альфа-книга", "978-5-9922-2492-4", 314, true, false, true));
        books.add(new Book(2, "Портрет с пулей в челюсти", "Ханна Кралль", createDateFromString("2017"), "Corpus", "978-5-17-098823-5", 384, true, false, false));
        books.add(new Book(3, "Старик и шляпа", "Игорь Шевчук", createDateFromString("2017"), "Мещерякова ИД", "978-5-00108-134-0", 32, true, false, true));
        books.add(new Book(4, "Как я была маленькой", "Вера Желиховская", createDateFromString("2017"), "Речь", "978-5-9268-2583-8", 208, true, false, false));
        books.add(new Book(5, "Лесная газета. Осень", "Виталий Бианки", createDateFromString("2017"), "Мещерякова ИД", "978-5-00108-128-9", 96, true, false, true));
        books.add(new Book(6, "Исчезнувший мир", "Игорь Акимушкин", createDateFromString("2017"), "Мещерякова ИД", "978-5-00108-095-4", 192, true, false, false));
        books.add(new Book(7, "Цена вопроса", "Александра Маринина", createDateFromString("2017"), "Эксмо", "978-5-04-004675-1", 352, true, false, false));
        books.add(new Book(8, "В тихом омуте", "Пола Хокинс", createDateFromString("2017"), "АСТ", "978-5-17-104797-9", 384, true, false, true));
        books.add(new Book(9, "Шепот в темноте", "Елена Звездная", createDateFromString("2017"), "Эксмо", "978-5-699-96975-3", 352, true, false, false));
        books.add(new Book(10,"Июнь", "Дмитрий Быков", createDateFromString("2017"), "Редакция Елены Шубиной", "978-5-17-092368-7", 512, true, false, true));
        books.add(new Book(11,"Остров Сокровищ", "Роберт Стивенсон", createDateFromString("2017"), "Лабиринт", "978-5-9287-2526-6", 188, true, false, false));
        return books;
    }

    private Date createDateFromString(String year) {
        DateFormat dateformat = new SimpleDateFormat("yyyy");
        try {
            return dateformat.parse(year);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
