package com.newbee.drawdevelopmenttool.fragment.head.adapter;

        import android.content.Context;
        import android.text.TextUtils;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import androidx.recyclerview.widget.RecyclerView;
        import com.lixiao.build.glide.MyGlide;
        import com.lixiao.build.mybase.LG;
        import com.lixiao.build.mybase.appliction.BaseApplication;
        import com.lixiao.build.mybase.appliction.MyApplicationFile;
        import com.lixiao.build.util.TimeUtil;
        import com.newbee.drawdevelopmenttool.R;
        import com.newbee.drawdevelopmenttool.bean.content.ContentHeadBean;
        import com.newbee.drawdevelopmenttool.bean.content.ContentHeadRsType;
        import com.newbee.drawdevelopmenttool.eventbus.EventBusSubscriptionSubject;
        import com.newbee.drawdevelopmenttool.eventbus.EventType;
        import com.newbee.drawdevelopmenttool.fragment.head.content.HeadFragmentShowContentType;
        import com.newbee.drawdevelopmenttool.fragment.head.content.ManuscriptsContentShowModelType;
        import com.newbee.drawdevelopmenttool.sql.content.ContentHeadSqlServer;

        import java.io.File;
        import java.util.List;

public class ManuscriptsContentAdapter extends BaseContentHeadAdapter{


    public ManuscriptsContentAdapter(Context context, HeadFragmentShowContentType contentType, List<ContentHeadBean> contentHeadBeanList, ItemClick itemClick, ManuscriptsContentShowModelType showModelType, boolean adapterNeedAddContent, boolean adapterNeedRetrun) {
        super(context, contentType, contentHeadBeanList, itemClick, showModelType, adapterNeedAddContent, adapterNeedRetrun);
    }
}
