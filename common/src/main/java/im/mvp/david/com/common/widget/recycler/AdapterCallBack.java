package im.mvp.david.com.common.widget.recycler;

/**
 * @author David
 *
 */
public interface AdapterCallBack<Data> {
    void update(Data date, RecyclerAdapter.ViewHolder<Data> dataViewHolder);
}
