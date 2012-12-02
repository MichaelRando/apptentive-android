/*
 * Copyright (c) 2012, Apptentive, Inc. All Rights Reserved.
 * Please refer to the LICENSE file for the terms and conditions
 * under which redistribution and use of this file is permitted.
 */

package com.apptentive.android.sdk.module.messagecenter.view;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apptentive.android.sdk.R;
import com.apptentive.android.sdk.module.messagecenter.model.TextMessage;
import com.apptentive.android.sdk.util.Util;

/**
 * @author Sky Kelsey
 */
public class TextMessageView extends MessageView {


	public TextMessageView(Context context, TextMessage message) {
		super(context, message);
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		int dips5 = Util.dipsToPixels(context, 5);
		setPadding(0, dips5, 0, dips5);


		LinearLayout row = new LinearLayout(context);
		row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		row.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout spacer = new LinearLayout(context);
		LinearLayout.LayoutParams spacerParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
		spacerParams.weight = 1;
		spacer.setLayoutParams(spacerParams);

		LinearLayout textRow = new LinearLayout(context);
		LinearLayout.LayoutParams textRowParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT);
		textRowParams.weight = 3;
		textRow.setLayoutParams(textRowParams);
		textRow.setOrientation(LinearLayout.VERTICAL);
		textRow.setGravity(message.isOutgoingMessage() ? Gravity.RIGHT : Gravity.LEFT);

		TextView textView = new TextView(context);
		LayoutParams textViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textView.setLayoutParams(textViewParams);
		textView.setTextColor(Color.BLACK);
		textView.setText(message.getBody());
		textView.setBackgroundResource(message.isOutgoingMessage() ? R.drawable.apptentive_message_outgoing : R.drawable.apptentive_message_incoming);
		textView.setGravity(Gravity.CENTER_VERTICAL);

		TextView timestampView = new TextView(context);
		timestampView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		timestampView.setText(Util.dateToDisplayString(Math.round(message.getCreatedAt() * 1000)));
		timestampView.setPadding(0, 0, Util.dipsToPixels(context, 10), 0);
		timestampView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
		timestampView.setTextColor(Color.parseColor("#777777"));
		timestampView.setShadowLayer(1, 0, 0.5f, Color.WHITE);

		textRow.addView(textView);
		textRow.addView(timestampView);
		if (message.isOutgoingMessage()) {
			row.addView(spacer);
			row.addView(textRow);
		} else {
			row.addView(textRow);
			row.addView(spacer);
		}
		addView(row);
	}
}