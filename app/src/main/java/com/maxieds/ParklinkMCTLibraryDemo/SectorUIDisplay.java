package com.maxieds.ParklinkMCTLibraryDemo;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.Locale;

import com.maxieds.ParklinkMCTLibraryDemo.R;
import com.maxieds.MifareClassicToolLibrary.MCTUtils;

public class SectorUIDisplay {

     private LinearLayout mainLayoutContainer;

     public SectorUIDisplay() {
          mainLayoutContainer = new LinearLayout(MainActivity.mainActivityInstance);
          mainLayoutContainer.setOrientation(LinearLayout.VERTICAL);
          LayoutInflater sectorLayoutInflater = MainActivity.mainActivityInstance.getLayoutInflater();
          LinearLayout sectorLayout = (LinearLayout) sectorLayoutInflater.inflate(R.layout.sector_ui_display, mainLayoutContainer, false);
          mainLayoutContainer.addView(sectorLayout);
     }

     public LinearLayout GetDisplayLayout() {
          return mainLayoutContainer;
     }

     public static SectorUIDisplay NewInstance(byte[][] sectorBlockData, int sectorIndex, boolean sectorReadFailed) {
          SectorUIDisplay sectorDisplay = new SectorUIDisplay();
          LinearLayout sectorMainLayoutContainer = sectorDisplay.GetDisplayLayout();
          TextView tvSectorDisplayHeader = (TextView) sectorMainLayoutContainer.findViewById(R.id.sectorDisplayHeaderText);
          tvSectorDisplayHeader.setText(String.format(Locale.US, "Sector %02d", sectorIndex));
          if(!sectorReadFailed) {
               tvSectorDisplayHeader.append(" -- (READ FAILED)");
          }
          TextView tvSectorDisplayBytes = (TextView) sectorMainLayoutContainer.findViewById(R.id.sectorDisplayBytesText);
          for(int blk = 0; blk < sectorBlockData.length; blk++) {
               String blockHexBytesText = MCTUtils.BytesToHexString(sectorBlockData[blk]);
               String blockHexBytesTextSep = new String();
               for(int spos = 0; spos < blockHexBytesText.length(); spos += 2) {
                    blockHexBytesTextSep += blockHexBytesText.substring(spos, spos + 2);
                    if(spos < blockHexBytesText.length() - 2) {
                         blockHexBytesTextSep += ":";
                    }
               }
               if(blk > 0) {
                    tvSectorDisplayBytes.append("\n");
               }
               tvSectorDisplayBytes.append(blockHexBytesTextSep);
          }
          return sectorDisplay;
     }

}
