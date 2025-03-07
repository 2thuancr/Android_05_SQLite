package com.student22110006.sqliteapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.student22110006.sqliteapplication.R;
import com.student22110006.sqliteapplication.model.NotesModel;
import com.student22110006.sqliteapplication.ui.MainActivity;

import java.util.List;

public class NotesAdapter extends BaseAdapter {
    // Khai báo biến toàn cục
    private MainActivity context; //điều chỉnh biến context
    private int layout;
    private List<NotesModel> noteList;

    // Tạo constructor
    public NotesAdapter(MainActivity context, int layout, List<NotesModel> noteList) {
        this.context = context;
        this.layout = layout;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Tạo ViewHolder
    private class ViewHolder {
        TextView textViewNote;
        ImageView imageViewEdit;
        ImageView imageViewDelete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gọi ViewHolder
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.textViewNote = (TextView) convertView.findViewById(R.id.textViewNameNote);
            viewHolder.imageViewDelete = (ImageView) convertView.findViewById(R.id.imageViewDelete);
            viewHolder.imageViewEdit = (ImageView) convertView.findViewById(R.id.imageViewEdit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lấy giá trị
        // Lấy giá trị Notes tại vị trí hiện tại
        final NotesModel notes = noteList.get(position);
        viewHolder.textViewNote.setText(notes.getNameNote());
        // Bắt sự kiện khi nhấn vào nút chỉnh sửa
        viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị thông báo cập nhật
                Toast.makeText(context, "Cập nhật " + notes.getNameNote(), Toast.LENGTH_SHORT).show();

                // Gọi Dialog cập nhật Notes từ MainActivity.java
                ((MainActivity) context).DialogCapNhatNotes(notes.getNameNote(), notes.getIdNote());
            }
        });

        // Bắt sự kiện xóa Notes
        viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi hộp thoại xác nhận xóa Notes từ MainActivity.java
                ((MainActivity) context).DialogDelete(notes.getNameNote(), notes.getIdNote());
            }
        });

        return convertView;
    }

}

