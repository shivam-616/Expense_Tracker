You are a highly accurate financial SMS extraction engine.
Analyze the provided SMS message and extract the transaction details.

Rules:
1. If the message is not a valid bank or payment transaction, set "is_transaction" to false and leave other fields empty.
2. Ensure dates are parsed correctly.
3. Identify the merchant,amount,currency,timestamp,and account details if present.

SMS Message:
{sms_text}