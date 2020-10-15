package com.example.bookaroom.ui.tableView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.example.bookaroom.R;
import com.example.bookaroom.ui.tableView.model.Cell;
import com.example.bookaroom.ui.tableView.model.ColumnHeader;
import com.example.bookaroom.ui.tableView.model.RowHeader;

public class TableViewAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {

    /**
     * This is sample CellViewHolder class
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    class CellViewHolder extends AbstractViewHolder {

        final LinearLayout cellContainer;
        final TextView cellTextView;

        public CellViewHolder(View itemView) {
            super(itemView);
            cellContainer = itemView.findViewById(R.id.cell_container);
            cellTextView = itemView.findViewById(R.id.cell_data);

        }
    }

    /**
     * This is where you create your custom Cell ViewHolder. This method is called when Cell
     * RecyclerView of the TableView needs a new RecyclerView.ViewHolder of the given type to
     * represent an item.
     *
     * @param viewType : This value comes from #getCellItemViewType method to support different type
     *                 of viewHolder as a Cell item.
     *
     * @see #getCellItemViewType(int);
     */
    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        // Get cell xml layout
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_cell_layout, parent, false);
        // Create a Custom ViewHolder for a Cell item.
        return new CellViewHolder(layout);
    }

    /**
     * That is where you set Cell View Model data to your custom Cell ViewHolder. This method is
     * Called by Cell RecyclerView of the TableView to display the data at the specified position.
     * This method gives you everything you need about a cell item.
     *
     * @param holder       : This is one of your cell ViewHolders that was created on
     *                     ```onCreateCellViewHolder``` method. In this example, we have created
     *                     "MyCellViewHolder" holder.
     * @param cellItemModel     : This is the cell view model located on this X and Y position. In this
     *                     example, the model class is "Cell".
     * @param columnPosition : This is the X (Column) position of the cell item.
     * @param rowPosition : This is the Y (Row) position of the cell item.
     *
     * @see #onCreateCellViewHolder(ViewGroup, int);
     */
    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Cell cellItemModel, int
            columnPosition, int rowPosition) {
        Cell cell = (Cell) cellItemModel;

        // Get the holder to update cell item text
        CellViewHolder viewHolder = (CellViewHolder) holder;
        if (viewHolder.cellTextView == null) {
            return;
        }
        String cellContent = cell.getData();
        viewHolder.cellTextView.setText(cellContent != null ? cellContent : "null");

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
        viewHolder.cellContainer.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        viewHolder.cellTextView.requestLayout();
    }


    /**
     * This is sample ColumnHeaderViewHolder class.
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    class ColumnHeaderViewHolder extends AbstractViewHolder {

        final LinearLayout columnHeaderContainer;
        final TextView columnHeaderTextView;

        public ColumnHeaderViewHolder(View itemView) {
            super(itemView);
            columnHeaderContainer = itemView.findViewById(R.id.column_header_container);
            columnHeaderTextView = itemView.findViewById(R.id.column_header_textView);
        }
    }

    /**
     * This is where you create your custom Column Header ViewHolder. This method is called when
     * Column Header RecyclerView of the TableView needs a new RecyclerView.ViewHolder of the given
     * type to represent an item.
     *
     * @param viewType : This value comes from "getColumnHeaderItemViewType" method to support
     *                 different type of viewHolder as a Column Header item.
     *
     * @see #getColumnHeaderItemViewType(int);
     */
    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Column Header xml Layout
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_column_header_layout, parent, false);

        // Create a ColumnHeader ViewHolder
        return new ColumnHeaderViewHolder(layout);
    }

    /**
     * That is where you set Column Header View Model data to your custom Column Header ViewHolder.
     * This method is Called by ColumnHeader RecyclerView of the TableView to display the data at
     * the specified position. This method gives you everything you need about a column header
     * item.
     *
     * @param holder   : This is one of your column header ViewHolders that was created on
     *                 ```onCreateColumnHeaderViewHolder``` method. In this example we have created
     *                 "MyColumnHeaderViewHolder" holder.
     * @param columnHeaderItemModel : This is the column header view model located on this X position. In this
     *                 example, the model class is "ColumnHeader".
     * @param position : This is the X (Column) position of the column header item.
     *
     * @see #onCreateColumnHeaderViewHolder(ViewGroup, int) ;
     */
    @Override
    public void onBindColumnHeaderViewHolder(@NonNull AbstractViewHolder holder, ColumnHeader columnHeaderItemModel, int
            position) {
        ColumnHeader columnHeader = (ColumnHeader) columnHeaderItemModel;

        // Get the holder to update cell item text
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;
        if (columnHeaderViewHolder.columnHeaderTextView == null) {
            return;
        }
        String colContent = columnHeader.getData();
        columnHeaderViewHolder.columnHeaderTextView.setText(colContent != null ? colContent : "null");

        // If your TableView should have auto resize for cells & columns.
        // Then you should consider the below lines. Otherwise, you can ignore them.

        // It is necessary to remeasure itself.
        columnHeaderViewHolder.columnHeaderContainer.getLayoutParams().width = LinearLayout
                .LayoutParams.WRAP_CONTENT;
        columnHeaderViewHolder.columnHeaderTextView.requestLayout();
    }

    /**
     * This is sample RowHeaderViewHolder class.
     * This viewHolder must be extended from AbstractViewHolder class instead of RecyclerView.ViewHolder.
     */
    class RowHeaderViewHolder extends AbstractViewHolder {

        final TextView rowHeaderTextView;

        public RowHeaderViewHolder(View itemView) {
            super(itemView);
            rowHeaderTextView = itemView.findViewById(R.id.row_header_textView);
        }
    }

    /**
     * This is where you create your custom Row Header ViewHolder. This method is called when
     * Row Header RecyclerView of the TableView needs a new RecyclerView.ViewHolder of the given
     * type to represent an item.
     *
     * @param viewType : This value comes from "getRowHeaderItemViewType" method to support
     *                 different type of viewHolder as a row Header item.
     *
     * @see #getRowHeaderItemViewType(int);
     */
    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_row_header_layout, parent, false);

        // Create a Row Header ViewHolder
        return new RowHeaderViewHolder(layout);
    }


    /**
     * That is where you set Row Header View Model data to your custom Row Header ViewHolder. This
     * method is Called by RowHeader RecyclerView of the TableView to display the data at the
     * specified position. This method gives you everything you need about a row header item.
     *
     * @param holder   : This is one of your row header ViewHolders that was created on
     *                 ```onCreateRowHeaderViewHolder``` method. In this example, we have created
     *                 "MyRowHeaderViewHolder" holder.
     * @param rowHeaderItemModel : This is the row header view model located on this Y position. In this
     *                 example, the model class is "RowHeader".
     * @param position : This is the Y (row) position of the row header item.
     *
     * @see #onCreateRowHeaderViewHolder(ViewGroup, int) ;
     */

    public void onBindRowHeaderViewHolder(@NonNull AbstractViewHolder holder, RowHeader rowHeaderItemModel, int
            position) {
        RowHeader rowHeader = (RowHeader) rowHeaderItemModel;

        // Get the holder to update row header item text
        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        if (rowHeaderViewHolder.rowHeaderTextView == null) {
            return;
        }
        String rowContent = rowHeader.getData();
        rowHeaderViewHolder.rowHeaderTextView.setText(rowContent != null ? rowContent : "null");
    }

    @Override
    public View onCreateCornerView(ViewGroup parent) {
        // Get Corner xml layout
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_view_corner_layout, parent, false);
    }

    @Override
    public int getColumnHeaderItemViewType(int columnPosition) {
        // The unique ID for this type of column header item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of ColumnViewHolder on "onCreateColumnViewHolder"
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int rowPosition) {
        // The unique ID for this type of row header item
        // If you have different items for Row Header View by Y (Row) position,
        // then you should fill this method to be able create different
        // type of RowHeaderViewHolder on "onCreateRowHeaderViewHolder"
        return 0;
    }

    @Override
    public int getCellItemViewType(int columnPosition) {
        // The unique ID for this type of cell item
        // If you have different items for Cell View by X (Column) position,
        // then you should fill this method to be able create different
        // type of CellViewHolder on "onCreateCellViewHolder"
        return 0;
    }
}