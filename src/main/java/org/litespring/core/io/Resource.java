package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dengrd on 2018/6/18.
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    String getDescription();
}
