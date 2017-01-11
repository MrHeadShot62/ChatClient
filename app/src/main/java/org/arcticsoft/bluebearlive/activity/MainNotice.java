package org.arcticsoft.bluebearlive.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.arcticsoft.bluebearlive.R;
import org.arcticsoft.bluebearlive.core.Notice.Notice;
import org.arcticsoft.bluebearlive.core.Notice.NoticeAdapter;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;

/**
 * Created by DmitriyRoot on 09.01.2017.
 */

public class MainNotice {

    private View view;

    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private SwipeToAction swipeToAction;

    private List<Notice> books = new ArrayList<>();

    public MainNotice(View view) {
        this.view = view;

        Fresco.initialize(view.getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new NoticeAdapter(this.books);
        recyclerView.setAdapter(adapter);

        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Notice>() {
            @Override
            public boolean swipeLeft(final Notice itemData) {
                final int pos = removeNotice(itemData);
                displayInfoNotice(itemData.getTitle() + " remove");
                return true;
            }

            @Override
            public boolean swipeRight(Notice itemData) {
                final int pos = removeNotice(itemData);
                displayInfoNotice(itemData.getTitle() + " remove");
                return true;
            }

            @Override
            public void onClick(Notice itemData) {
                displayInfoNotice(itemData.getTitle() + " clicked");
            }

            @Override
            public void onLongClick(Notice itemData) {
                displayInfoNotice(itemData.getTitle() + " long clicked");
            }
        });

        populate();

    }

    private void populate() {
        this.books.add(new Notice("Вас оценили", "Какая то информация", "http://static.bookstck.com/books/einstein-his-life-and-universe-400.jpg"));
        this.books.add(new Notice("Zero to One: Notes on Startups, or How to Build the Future", "Peter Thiel, Blake Masters", "http://static.bookstck.com/books/zero-to-one-400.jpg"));
        this.books.add(new Notice("Tesla: Inventor of the Electrical Age", "W. Bernard Carlson", "http://static.bookstck.com/books/tesla-inventor-of-the-electrical-age-400.jpg"));
        this.books.add(new Notice("Orwell's Revenge: The \"1984\" Palimpsest", "Peter Huber", "http://static.bookstck.com/books/orwells-revenge-400.jpg"));
        this.books.add(new Notice("How to Lie with Statistics", "Darrell Huff", "http://static.bookstck.com/books/how-to-lie-with-statistics-400.jpg"));
        this.books.add(new Notice("Abundance: The Future Is Better Than You Think", "Peter H. Diamandis, Steven Kotler", "http://static.bookstck.com/books/abundance-400.jpg"));
        this.books.add(new Notice("Where Good Ideas Come From", "Steven Johnson", "http://static.bookstck.com/books/where-good-ideas-come-from-400.jpg"));
        this.books.add(new Notice("The Information: A History, A Theory, A Flood", "James Gleick", "http://static.bookstck.com/books/the-information-history-theory-flood-400.jpg"));
        this.books.add(new Notice("Turing's Cathedral: The Origins of the Digital Universe", "George Dyson", "http://static.bookstck.com/books/turing-s-cathedral-400.jpg"));
    }

    private void displayInfoNotice(String text) {
        Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
    }

    private int removeNotice(Notice book) {
        int pos = books.indexOf(book);
        books.remove(book);
        adapter.notifyItemRemoved(pos);
        return pos;
    }

    private void newNotice(int pos, Notice book) {
        books.add(pos, book);
        adapter.notifyItemInserted(pos);
    }
}
