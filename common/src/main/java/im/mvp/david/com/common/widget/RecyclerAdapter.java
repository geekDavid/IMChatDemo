package im.mvp.david.com.common.widget;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import im.mvp.david.com.common.R;

/**
 * @author David
 */

public abstract class RecyclerAdapter<Data> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Data>>
        implements View.OnClickListener, View.OnLongClickListener, AdapterCallBack<Data> {

    private final List<Data> mData;
    private AdapterLinstener mLinstener;

    /**
     * 构造函数模块
     */
    public RecyclerAdapter() {
        this(null);
    }

    public RecyclerAdapter(AdapterLinstener linstener) {
        this(new ArrayList<Data>(), linstener);
        this.mLinstener = linstener;
    }

    public RecyclerAdapter(List<Data> data, AdapterLinstener linstener) {
        this.mData = data;
        this.mLinstener = linstener;
    }

    /**
     * 添一条数据
     *
     * @param data 数据对象
     */
    protected void add(Data data) {
        if (data != null) {
            mData.add(data);
            notifyItemInserted(mData.size() - 1);
        }
    }

    /**
     * 添加一段数据
     *
     * @param data 数据对象
     */
    protected void add(Data... data) {
        if (data != null && data.length > 0) {
            int startPosition = mData.size();
            Collections.addAll(mData, data);
            notifyItemRangeInserted(startPosition, data.length);
        }
    }

    /**
     * 添加整个列表数据
     *
     * @param list 数据集合
     */
    protected void add(Collection<Data> list) {
        if (list != null && list.size() > 0) {
            int startPosition = mData.size();
            mData.addAll(list);
            notifyItemRangeChanged(startPosition, list.size());
        }
    }

    /**
     * 清除数据
     */
    protected void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 替换
     *
     * @param list 数据集合
     */
    protected void replace(Collection<Data> list) {
        clear();
        if (list == null && list.size() == 0)
            return;
        mData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder<Data> onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //将布局id转换成View
        View root = inflater.inflate(viewType, parent, false);
        //通过子类必须继承得到ViewHolder
        ViewHolder holder = onCreateViewHolder(root, viewType);
        //设置View的tag为holder，进行双向绑定
        root.setTag(R.id.tag_recycler_holder, holder);
        //设置点击事件
        root.setOnClickListener(this);
        //设置长按事件
        root.setOnLongClickListener(this);
        //进行界面注解绑定
        holder.unbinder = ButterKnife.bind(holder, root);
        //设置回调为自己
        holder.callBack = this;
        return holder;
    }

    /**
     * 创建ViewHolder
     *
     * @param root     跟布局
     * @param viewType 布局类型，其实就是XML的ID
     * @return
     */
    protected abstract ViewHolder<Data> onCreateViewHolder(View root, int viewType);

    @Override
    public void onBindViewHolder(ViewHolder<Data> holder, int position) {
        //获取相应数据
        Data data = mData.get(position);
        //绑定holder数据
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return (mData != null && mData.size() > 0) ? mData.size() : 0;
    }

    @Override
    public void onClick(View view) {
        //回去tag绑定的holder
        ViewHolder holder = (ViewHolder) view.getTag(R.id.tag_recycler_holder);
        if (mLinstener != null) {
            //获取当前坐标
            int pos = holder.getAdapterPosition();
            //点击回调
            mLinstener.onItemClickLinstener(pos, mData.get(pos));
        }
    }

    @Override
    public boolean onLongClick(View view) {
        //回去tag绑定的holder
        ViewHolder holder = (ViewHolder) view.getTag(R.id.tag_recycler_holder);
        if (mLinstener != null) {
            //获取当前坐标
            int pos = holder.getAdapterPosition();
            //点击回调
            mLinstener.onItemLongClickLinstener(pos, mData.get(pos));
            return true;
        }
        return false;
    }


    /**
     * 自定义适配器事件接口
     *
     * @param <Data>
     */
    public interface AdapterLinstener<Data> {
        /**
         * 适配器item单机事件
         *
         * @param position item位置指针
         * @param data     item对应的数据
         */
        void onItemClickLinstener(int position, Data data);

        /**
         * 适配器item长按事件
         *
         * @param position item位置指针
         * @param data     item对应的数据
         */
        void onItemLongClickLinstener(int position, Data data);
    }

    /**
     * 设置事件
     *
     * @param linstener
     */
    public void setLinstener(AdapterLinstener<Data> linstener) {
        this.mLinstener = linstener;
    }

    public abstract static class ViewHolder<Data> extends RecyclerView.ViewHolder {

        private AdapterCallBack callBack;
        protected Unbinder unbinder;
        protected Data mData;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * 绑定数据时触发
         *
         * @param data 数据对象
         */
        void bind(Data data) {
            mData = data;
        }

        /**
         * 绑定数据时回调
         *
         * @param data 数据对象
         */
        protected abstract void onBind(Data data);

        /**
         * holder自己对自己进行的更新操作
         *
         * @param data
         */
        protected void update(Data data) {
            if (callBack != null) {
                callBack.update(data, this);
            }
        }

    }
}
