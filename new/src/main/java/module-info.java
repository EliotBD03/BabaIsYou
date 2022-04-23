module java{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens View to javafx.fxml;
    exports View;
    exports Presenter;
    exports Model;
}