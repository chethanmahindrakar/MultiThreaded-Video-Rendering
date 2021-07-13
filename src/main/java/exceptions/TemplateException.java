package exceptions;

import java.io.IOException;

public class TemplateException extends IOException {

        public TemplateException(String message, Exception e)
        {

            super(message, e);
        }

        public TemplateException(String message)
        {
            super(message);
        }
    }

