from flask import Flask, render_template
import requests
import json

app = Flask(__name__)

@app.route('/studyPlans/all')
def get_type():
    records = json.loads(requests.get('http://127.0.0.1:8080/studyPlans/all').text)
    return render_template('Type.html', records=records)

@app.route('/student_mark')
def get_student_mark():
    records = json.loads(requests.get('http://127.0.0.1:8080/student_mark').text)
    return render_template('StudentMark.html', records=records)

@app.route('/retake')
def get_retake():
    records = json.loads(requests.get('http://127.0.0.1:8080/retake').text)
    return render_template('Retake.html', records=records)

if __name__ == '__main__':
    app.run()