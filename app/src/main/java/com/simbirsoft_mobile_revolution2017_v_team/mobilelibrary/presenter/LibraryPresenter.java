package com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.presenter;

import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.Book;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.domain.BookBuilder;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.IRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.repository.LibraryRepository;
import com.simbirsoft_mobile_revolution2017_v_team.mobilelibrary.view.ILibraryView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LibraryPresenter {

    private ILibraryView view;
    private final IRepository libraryRepository = new LibraryRepository();

    public void attachView(ILibraryView view) {
        this.view = view;
    }

    public void loadLibrary() {
        libraryRepository.getBooks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                            if (view != null) {
                                view.onDataReceived(books);
                            }
                        }, throwable -> {
                            if (view != null) {
                                view.showError();
                            }
                        }
                );

    }

    public void addBook(String name, String author, Date year, String publishingHouse,
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
                                view.showError();
                            }
                        }
                );
    }

    public void loadFavourites() {
        libraryRepository.getBooks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                            if (view != null) {
                                view.onDataReceived(books.stream().filter(book -> book.isFavourite() == true).collect(Collectors.<Book>toList()));
                            }
                        }, throwable -> {
                            if (view != null) {
                                view.showError();
                            }
                        }
                );
    }

    public void loadBook(String id) {
        libraryRepository.getBooks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books -> {
                            if (view != null) {
                                view.onDataReceived(books.stream().filter(book -> book.getId() == id).findAny().orElse(null));
                            }
                        }, throwable -> {
                            if (view != null) {
                                view.showError();
                            }
                        }
                );
    }

    public void detachView() {
        view = null;
    }
}
