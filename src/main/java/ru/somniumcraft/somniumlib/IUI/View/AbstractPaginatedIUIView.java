package ru.somniumcraft.somniumlib.IUI.View;

import net.kyori.adventure.text.TextComponent;
import ru.somniumcraft.somniumlib.IUI.Element.AbstractIUIElement;
import ru.somniumcraft.somniumlib.IUI.PaginatedList;
import ru.somniumcraft.somniumlib.IUI.Presenter.AbstractPaginatedIUIPresnter;

public abstract class AbstractPaginatedIUIView extends AbstractIUIView<AbstractPaginatedIUIPresnter, PaginatedList>{

    public AbstractPaginatedIUIView(AbstractPaginatedIUIPresnter presenter, TextComponent name) {
        super(presenter, 6, name);
    }

    //Настраиваем отображение объектов из списка
    public abstract void setPageContents(PaginatedList data);


    @Override
    public void setItems(PaginatedList data) {
        setPageContents(data);
/*
        if(data.hasPreviousPage())
        //    this.IUIElements.add(new PreviousPageIUIElement(48)
        //            .setClickHandler(x->{
        //                presenter.open();
        //            }));

        this.IUIElements.add(
                //new PageNumberIUIElement(49, data.getPageNumber())
                );

        if(data.hasNextPage())
        //    this.IUIElements.add(new NextPageIUIElement(50)
        //        .setClickHandler(x->{
        //            presenter.open();
        //        }));
*/
    }


}
