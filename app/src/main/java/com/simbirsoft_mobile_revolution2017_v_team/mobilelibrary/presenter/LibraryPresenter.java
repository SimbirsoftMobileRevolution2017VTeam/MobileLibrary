package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookBuilder;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.IRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.LibraryRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LibraryPresenter {

    private List<Book> library = createFictionData(new ArrayList<>());
    private ILibraryView view;
    private final IRepository libraryRepository = new LibraryRepository();

    public void attachView(ILibraryView view) {
        this.view = view;
    }

    public void loadLibrary() {
        libraryRepository.getBooks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    books -> {
                        if (view != null) {
                            view.onDataReceived(books);
                        }
                    },
                    throwable -> {
                        if (view != null) {
                            view.onError(throwable);
                            tryCreateNewForShowing(library);
                        }
                    }
                );

    }

    public void addBook(String name, String author, long year, String publishingHouse,
                        String ISBN, int numberOfPages, boolean isAvailable, boolean wasReaded,
                        boolean isFavourite) {

        Book book = new BookBuilder()
                .name(name)
                .author(author)
                .year(year)
                .publishingHouse(publishingHouse)
                .ISBN(ISBN)
                .numberOfPages(numberOfPages)
                .isAvailable(isAvailable)
                .wasReaded(wasReaded)
                .isFavourite(isFavourite)
                .build();

        libraryRepository.addBook(book)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                            if (view != null) {
                                //показать сообщение "Запись добавлена"???
                                //Выполнить onDataCreated(Book book)????
                            }
                        }, throwable -> {
                            if (view != null) {
                                view.onError(throwable);
                            }
                        }
                );
    }

    public void loadFavourites() {
        libraryRepository.getFavouriteBooks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                            if (view != null) {
                                view.onDataReceived(books);
                            }
                        }, throwable -> {
                            if (view != null) {
                                view.onError(throwable);
                                tryCreateNewForShowing(getFavouriteBooks(library));
                            }
                        }
                );

    }

    public void loadBook(String id) {
        libraryRepository.getBookById(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(book -> {
                            if (view != null) {
                                view.onDataReceived(book);
                            }
                        }, throwable -> {
                            if (view != null) {
                                view.onError(throwable);
                                getFictionBook(id);
                            }
                        }
                );
    }

    public void detachView() {
        view = null;
    }

    private void tryCreateNewForShowing(List<Book> createdLibrary){
        Observable<List<Book>> observable = Observable.defer(new Callable<ObservableSource<? extends List<Book>>>() {
            @Override
            public ObservableSource<? extends List<Book>> call() throws Exception {
                return Observable.just(createdLibrary);
            }
        });
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    books -> {
                        if (view != null) {
                            view.onDataReceived(books);
                        }
                    },
                    throwable -> {
                        if (view != null) {
                            view.onError(throwable);
                        }
                    }
                );
    }

    private List<Book> createFictionData(List<Book> books){
        books.add(new BookBuilder()
                .id(String.valueOf(0))
                .name("Снежная Королева")
                .author("Ханс Андерсен")
                .year(2017)
                .publishingHouse("Верже ИД")
                .ISBN("978-5-9069-2803-0")
                .numberOfPages(72)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(false)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(1))
                .name("Незнакомка")
                .author("Анна Рэй")
                .year(2017)
                .publishingHouse("Альфа-книга")
                .ISBN("978-5-9922-2492-4")
                .numberOfPages(314)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(true)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(2))
                .name("Портрет с пулей в челюсти")
                .author("Ханна Кралль")
                .year(2017)
                .publishingHouse("Corpus")
                .ISBN("978-5-17-098823-5")
                .numberOfPages(84)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(false)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(3))
                .name("Старик и шляпа")
                .author("Игорь Шевчук")
                .year(2017)
                .publishingHouse("Мещерякова ИД")
                .ISBN("978-5-00108-134-0")
                .numberOfPages(72)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(true)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(4))
                .name("Как я была маленькой")
                .author("Вера Желиховская")
                .year(2017)
                .publishingHouse("Речь")
                .ISBN("978-5-9268-2583-8")
                .numberOfPages(208)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(false)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(5))
                .name("Лесная газета. Осень")
                .author("Виталий Бианки")
                .year(2017)
                .publishingHouse("Мещерякова ИД")
                .ISBN("978-5-00108-128-9")
                .numberOfPages(96)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(true)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(6))
                .name("Исчезнувший мир")
                .author("Игорь Акимушкин")
                .year(2017)
                .publishingHouse("Мещерякова ИД")
                .ISBN("978-5-00108-095-4")
                .numberOfPages(192)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(false)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(7))
                .name("Цена вопроса")
                .author("Александра Маринина")
                .year(2017)
                .publishingHouse("Эксмо")
                .ISBN("978-5-04-004675-1")
                .numberOfPages(352)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(true)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(8))
                .name("В тихом омуте")
                .author("Пола Хокинс")
                .year(2017)
                .publishingHouse("АСТ")
                .ISBN("978-5-17-104797-9")
                .numberOfPages(384)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(true)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(9))
                .name("Шепот в темноте")
                .author("Елена Звездная")
                .year(2017)
                .publishingHouse("Эксмо")
                .ISBN("978-5-699-96975-3")
                .numberOfPages(352)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(false)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(10))
                .name("Июнь")
                .author("Дмитрий Быков")
                .year(2017)
                .publishingHouse("Редакция Елены Шубиной")
                .ISBN("978-5-17-092368-7")
                .numberOfPages(512)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(true)
                .build());
        books.add(new BookBuilder()
                .id(String.valueOf(11))
                .name("Остров Сокровищ")
                .author("Роберт Стивенсон")
                .year(2017)
                .publishingHouse("Лабиринт")
                .ISBN("978-5-9287-2526-6")
                .numberOfPages(188)
                .isAvailable(true)
                .wasReaded(false)
                .isFavourite(false)
                .build());
        return books;
    }

    private List<Book> getFavouriteBooks(List<Book> books) {
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()) {
            Book next = iterator.next();
            if(!next.isFavourite()) {
                iterator.remove();
            }
        }
        return books;
    }

    private void getFictionBook(String id){
        Observable<Book> observable = Observable.defer(new Callable<ObservableSource<? extends Book>>() {
            @Override
            public ObservableSource<? extends Book> call() throws Exception {
                return Observable.just(getBookByIdFromCreatedLibrary(id));
            }
        });
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        book -> {
                            if (view != null) {
                                view.onDataReceived(book);
                            }
                        },
                        throwable -> {
                            if (view != null) {
                                view.onError(throwable);
                            }
                        }
                );
    }

    private Book getBookByIdFromCreatedLibrary(String id){
        if(library.size() == 0){
            return null;
        }
        for (Book book:library) {
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }
}
