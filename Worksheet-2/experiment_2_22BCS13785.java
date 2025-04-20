class experiment2_22BCS13785 {

    static class Video {
        String title;
        boolean checked_out;
        double avg_rating;
        int rating;
        int total_rating;

        public Video(String title) {
            this.title = title;
            this.checked_out = false;
            this.avg_rating = 0;
            this.rating = 0;
            this.total_rating = 0;
        }

        public String Title_show() {
            return title;
        }

        public boolean check_out() {
            return checked_out;
        }

        public void checking() {
            if (!checked_out) {
                checked_out = true;
                System.out.println(title + " is checked out");
            } else {
                System.out.println(title + " is already checked out");
            }
        }

        public void returning() {
            if (checked_out) {
                checked_out = false;
                System.out.println(title + " is returned");
            } else {
                System.out.println(title + " is already returned");
            }
        }

        public void receive_rating(int rating) {
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating. Please give a rating between 1 and 5.");
                return;
            }
            total_rating += rating;
            this.rating++;
            avg_rating = (double) total_rating / this.rating;
            System.out.println("Rating for " + title + " is: " + avg_rating);
        }

        public void display() {
            System.out.println(title + ", Checked Out: " + checked_out + " is: " + avg_rating);
        }

        public String get_title() {
            return title;
        }
    }

    static class video_store {
        Video[] videos;
        int video_count;

        public video_store() {
            videos = new Video[5];
            video_count = 0;
        }

        public void add(String title) {
            if (video_count < 5) {
                videos[video_count++] = new Video(title);
                System.out.println("Added video: " + title);
            } else {
                System.out.println("Inventory full.");
            }
        }

        public void check_out(String title) {
            Video video = find_video(title);
            if (video != null) {
                video.checking();
            } else {
                System.out.println("Video not found in inventory.");
            }
        }

        public void video_return(String title) {
            Video video = find_video(title);
            if (video != null) {
                video.returning();
            } else {
                System.out.println("Video not found in inventory.");
            }
        }

        public void rating_got(String title, int rating) {
            Video video = find_video(title);
            if (video != null) {
                video.receive_rating(rating);
            } else {
                System.out.println("Video not found in inventory.");
            }
        }

        public void inventory() {
            System.out.println("Inventory:");
            for (int i = 0; i < video_count; i++) {
                videos[i].display();
            }
        }

        public Video find_video(String title) {
            for (int i = 0; i < video_count; i++) {
                if (videos[i].get_title().equalsIgnoreCase(title)) {
                    return videos[i];
                }
            }
            return null;
        }

        public static void main(String[] args) {
            video_store store = new video_store();

            store.add("Matrix");
            store.add("God Father 2nd");
            store.add("Star wars");

            store.rating_got("Matrix", 3);
            store.rating_got("Matrix",2);
            store.rating_got("God Father 2nd", 4);
            store.rating_got("God Father 2nd", 5);
            store.rating_got("Star wars", 3);

            store.check_out("Matrix");
            store.video_return("Matrix");
            store.check_out("God Father 2nd");
         
        }
    }
}

