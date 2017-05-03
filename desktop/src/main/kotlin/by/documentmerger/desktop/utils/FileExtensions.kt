package by.documentmerger.desktop.utils

import java.io.File


fun File.isWordDocument(): Boolean {
    return this.extension == "docx" || this.extension == "doc"
}
