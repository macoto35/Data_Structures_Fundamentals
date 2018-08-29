
from collections import namedtuple

Request = namedtuple("Request", ["arrived_at", "time_to_process"])
Response = namedtuple("Response", ["was_dropped", "started_at"])

class Buffer:
    def __init__(self, buffer_size):
        self.buffer_size = buffer_size
        self.finish_time = []
        self.size = 0

    def _enqueue(self, i):

    def _dequeue(self):

    def process(self, request):


def process_requests(requests, buffer):
    responses = []
    for request in requests:
        responses.append(buffer.process(request))

    return responses


def main():
    buffer_size, n_requests = map(int, input().split())
    requests = []

    for _ in range(n_requests):
        arrived_at, time_to_process = map(int, input().split())
        requests.append(Request(arrived_at, time_to_process))

    buffer = Buffer(buffer_size)
    buffer.process(requests)
    responses = process_requests(requests, buffer)

    for response in responses:
        print(response.started_at if not response.was_dropped else -1)


if __name__ == '__main__':
    main()
