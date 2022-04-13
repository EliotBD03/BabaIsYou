module java{
    requires javafx.controls;
    requires javafx.fxml;

    opens View to javafx.fxml;
    exports View;
    exports Presenter;
    exports Model;
}