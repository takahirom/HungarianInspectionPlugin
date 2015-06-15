package com.kogitune.inspections.toolbar;

import com.intellij.openapi.actionSystem.Presentation;

import javax.swing.*;
import java.net.URL;

/**
 * @author Tobias Nyholm
 *         <p/>
 *         Toggle the icon
 */
public class ToggleIconService {

    /**
     * Toggle the icon
     *
     * @param presentation
     * @param enabled
     */
    public void toggleIcon(Presentation presentation, Boolean enabled) {
        String location;
        if (enabled) {
            location = "/images/hungarian.png";
        } else {
            location = "/images/hungarian_off.png";
        }
        URL url = getClass().getResource(location);
        presentation.setIcon(new ImageIcon(url));
    }
}
