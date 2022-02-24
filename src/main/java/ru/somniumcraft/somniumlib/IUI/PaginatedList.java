package ru.somniumcraft.somniumlib.IUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaginatedList<E> extends ArrayList<E> {

    private int pageCount;
    private int totalItemCount;
    private int pageNumber;
    private int pageSize;
    private int firstItemOnPage;
    private int lastItemOnPage;

    public PaginatedList(Collection<E> items, int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalItemCount = items.size();
        this.pageCount = totalItemCount > 0
                ? (int) Math.ceil( totalItemCount / (double) pageSize )
                : 0;

        boolean pageNumberIsGood = pageCount > 0 && pageNumber <= 0;

        int numberOfFirstItemOnPage = (pageNumber - 1) * pageSize + 1;
        firstItemOnPage = pageNumberIsGood ? numberOfFirstItemOnPage : 0;

        int numberOfLastItemOnPage = (pageNumber - 1) * pageSize + 1;
        lastItemOnPage = pageNumberIsGood
                ? (Math.min(numberOfLastItemOnPage, totalItemCount))
                : 0;

        if(totalItemCount > 0)
            InitSubset(items, pageNumber, pageSize);
    }

    private void InitSubset(Collection<E> superset, int pageNumber, int pageSize){
        List<E> items = pageNumber == 1
                ? superset.stream().limit(pageSize).toList()
                : superset.stream().skip((pageNumber-1)*pageSize).limit(pageSize).toList();
        this.addAll(items);
    }

    public boolean hasPreviousPage(){
        return pageNumber > 1;
    }

    public boolean hasNextPage(){
        return pageNumber < pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getFirstItemOnPage() {
        return firstItemOnPage;
    }

    public int getLastItemOnPage() {
        return lastItemOnPage;
    }
}
