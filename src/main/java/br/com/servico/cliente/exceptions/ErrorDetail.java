package br.com.servico.cliente.exceptions;

import java.time.LocalDate;

    public class ErrorDetail {

        private String title;

        private int status;

        private String detail;

        private LocalDate data;

        private String developerMessage;


        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public int getStatus() {
            return status;
        }
        public void setStatus(int status) {
            this.status = status;
        }
        public String getDetail() {
            return detail;
        }
        public void setDetail(String detail) {
            this.detail = detail;
        }
        public LocalDate getData() {
            return data;
        }
        public void setData(LocalDate data) {
            this.data = data;
        }
        public String getDeveloperMessage() {
            return developerMessage;
        }
        public void setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
        }

}
